-- Conexion a la base de datos
\c tbd_lab_1

-- Primero creamos la función que ejecutará el trigger
CREATE OR REPLACE FUNCTION actualizar_estado_producto()
RETURNS TRIGGER AS $$
BEGIN
    -- Si el nuevo stock es 0, actualizamos el estado a 'Agotado'
    IF NEW.stock = 0 THEN
        NEW.estado = 'Agotado';
    -- Si el stock vuelve a ser mayor que 0, actualizamos el estado a 'Disponible'
    ELSIF NEW.stock > 0 AND OLD.estado = 'Agotado' THEN
        NEW.estado = 'Disponible';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Luego creamos el trigger
CREATE OR REPLACE TRIGGER trigger_actualizar_estado_producto
    BEFORE UPDATE OF stock
    ON PRODUCTO
    FOR EACH ROW
    EXECUTE FUNCTION actualizar_estado_producto();

--Creamos la función para el trigger de auditoría
CREATE OR REPLACE FUNCTION log_operations()
RETURNS TRIGGER AS $$
DECLARE
    data JSONB;
    usuario_actual VARCHAR(255);
BEGIN

    SELECT current_setting('myapp.current_user', true) INTO usuario_actual;

    IF TG_OP = 'INSERT' THEN
        data := row_to_json(NEW);
    ELSIF TG_OP = 'UPDATE' THEN
        data := jsonb_build_object('antes', row_to_json(OLD), 'despues', row_to_json(NEW));
    ELSIF TG_OP = 'DELETE' THEN
        data := row_to_json(OLD);
    END IF;

    INSERT INTO audit_log(tabla, operacion, datos, usuario)
    VALUES (TG_TABLE_NAME, TG_OP, data, usuario_actual);

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER trigger_log_operations
AFTER INSERT OR UPDATE OR DELETE ON PRODUCTO
FOR EACH ROW EXECUTE FUNCTION log_operations();

-- -- Se crea el trigger especifico para guardar en la tabla de auditoría cuando se cambia el precio de un producto
-- CREATE OR REPLACE TRIGGER trigger_actualizar_precio_producto
--     AFTER UPDATE OF precio
--     ON PRODUCTO
--     FOR EACH ROW
--     EXECUTE FUNCTION log_operations();

-- Se crea el trigger especifico para guardar en la tabla de auditoría cuando se cambia el precio de un producto
CREATE OR REPLACE TRIGGER trigger_actualizar_precio_producto
    AFTER UPDATE OF precio
    ON PRODUCTO
    FOR EACH ROW
    WHEN (OLD.precio IS DISTINCT FROM NEW.precio)
    EXECUTE FUNCTION log_operations();

--Procedimiento almacenado que entrega un reporte con los usuarios que más queries realizan
CREATE OR REPLACE PROCEDURE reporte_audit()
LANGUAGE plpgsql
AS $$
DECLARE
    record RECORD;
BEGIN
    CREATE TEMP TABLE temp_reporte_audit AS
    SELECT 
        usuario,
        COUNT(CASE WHEN operacion = 'INSERT' THEN 1 END) AS total_inserts,
        COUNT(CASE WHEN operacion = 'UPDATE' THEN 1 END) AS total_updates,
        COUNT(CASE WHEN operacion = 'DELETE' THEN 1 END) AS total_deletes,
        JSONB_AGG(JSONB_BUILD_OBJECT(
            'fecha', fecha,
            'operacion', operacion,
            'datos', datos
        )) AS detalle
    FROM 
        audit_log
    GROUP BY 
        usuario
    ORDER BY 
        (COUNT(CASE WHEN operacion = 'INSERT' THEN 1 END) + 
         COUNT(CASE WHEN operacion = 'UPDATE' THEN 1 END) + 
         COUNT(CASE WHEN operacion = 'DELETE' THEN 1 END)) DESC;

    FOR record IN SELECT * FROM temp_reporte_audit LOOP
        RAISE NOTICE E'\n============================';
        RAISE NOTICE 'Usuario: %', record.usuario;
        RAISE NOTICE 'Total Inserts: %', record.total_inserts;
        RAISE NOTICE 'Total Updates: %', record.total_updates;
        RAISE NOTICE 'Total Deletes: %', record.total_deletes;
        RAISE NOTICE 'Detalle: %', record.detalle;
        RAISE NOTICE E'============================\n';
    END LOOP;

    -- Eliminar la tabla temporal después de mostrar los resultados
    DROP TABLE IF EXISTS temp_reporte_audit;

END;
$$;

-- Funcion para listar ordenes en un radio alrededor de una tienda especifica
CREATE OR REPLACE FUNCTION get_orders_within_radius(id_tienda_input INTEGER, radius_km DOUBLE PRECISION)
RETURNS TABLE (
    id_orden INTEGER,
    fecha_orden TEXT,
    estado VARCHAR(50),
    total DECIMAL(10, 2),
    id_cliente INTEGER,
    distancia_km DOUBLE PRECISION,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION 
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        o.id_orden,
        o.fecha_orden,
        o.estado,
        o.total,
        o.id_cliente,
        ST_DistanceSphere(
            c.coordenadas,
            t.coordenadas
        ) / 1000 AS distancia_km, 
        ST_Y(c.coordenadas) AS latitude, 
        ST_X(c.coordenadas) AS longitude 
    FROM 
        orden o
    INNER JOIN cliente c ON o.id_cliente = c.id_cliente
    INNER JOIN tienda t ON o.id_tienda = t.id_tienda
    WHERE 
        t.id_tienda = id_tienda_input
        AND ST_DistanceSphere(c.coordenadas, t.coordenadas) <= radius_km * 1000;
END;
$$ LANGUAGE plpgsql;

-- Funcion para encontrar repartidores dentro de un poligono
CREATE OR REPLACE FUNCTION get_delivery_in_polygon(id_poligono INTEGER)
RETURNS TABLE (
    id_repartidor INTEGER,
    nombre VARCHAR(255),
    id_orden INTEGER,
    fecha_orden TEXT,
    latitude DOUBLE PRECISION, 
    longitude DOUBLE PRECISION
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        r.id_repartidor,
        r.nombre,
        o.id_orden,
        o.fecha_orden,
        ST_Y(c.coordenadas) AS latitude,
        ST_X(c.coordenadas) AS longitude 
    FROM 
        orden o
    INNER JOIN repartidor r ON o.id_repartidor = r.id_repartidor
    INNER JOIN cliente c ON o.id_cliente = c.id_cliente
    INNER JOIN zona_reparto p ON ST_Within(c.coordenadas, p.poligono)
    WHERE 
        p.id_zona = id_poligono;
END;
$$ LANGUAGE plpgsql;

-- Funcion 24 id_tienda = 1 y radius 30 para realizar lo pedido
-- Procedimiento almacenado para obtener repartidores dentro de un radio específico desde una tienda
CREATE OR REPLACE FUNCTION get_repartidores_within_radius(id_tienda_input INTEGER, radius_km DOUBLE PRECISION) 
RETURNS TABLE (
    id_repartidor INTEGER,
    nombre VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        r.id_repartidor,
        r.nombre,
        ST_Y(r.coordenadas) AS latitude,
        ST_X(r.coordenadas) AS longitude
    FROM 
        repartidor r
    INNER JOIN tienda t ON ST_DistanceSphere(r.coordenadas, t.coordenadas) <= radius_km * 1000
    WHERE 
        t.id_tienda = id_tienda_input;
END;
$$ LANGUAGE plpgsql;

-- Procedimiento almacenado para obtener cliente dentro de un radio específico desde una tienda
CREATE OR REPLACE FUNCTION get_clientes_within_radius(id_tienda_input INTEGER, radius_km DOUBLE PRECISION) 
RETURNS TABLE (
    id_cliente INTEGER,
    username varchar(255),
    direccion varchar(255),
    email varchar(100),
    contrasena varchar(100),
    telefono varchar(20),
    rol varchar(20),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        c.id_cliente,
        c.username,
        c.direccion,
        c.email,
        c.contrasena,
        c.telefono,
        c.rol,
        ST_Y(c.coordenadas) AS latitude,
        ST_X(c.coordenadas) AS longitude
    FROM 
        cliente c
    INNER JOIN tienda t ON ST_DistanceSphere(c.coordenadas, t.coordenadas) <= radius_km * 1000
    WHERE 
        t.id_tienda = id_tienda_input;
END;
$$ LANGUAGE plpgsql;
