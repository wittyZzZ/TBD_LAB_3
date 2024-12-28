-- Conexion a la base de datos
\c tbd_lab_1

--55 ¿Cuál es el promedio de productos diferentes comprados por cliente en las órdenes en las que se gastó más de $500?
--Por ejemplo si un usuario tiene dos ordenes, una con 1 objeto distinto y otra con 2 objetos distintos, entonces su promedio de objetos distintos es de 1,5
SELECT 
    c.username AS "Nombre del Cliente",
    AVG(productos_diferentes) AS "Promedio cantidad de productos diferentes"
FROM (
    SELECT
        o.id_cliente,
        o.id_orden,
        COUNT(DISTINCT d.id_producto) AS productos_diferentes
    FROM ORDEN o
    JOIN DETALLE_ORDEN d ON o.id_orden = d.id_orden
    WHERE o.total > 500
    GROUP BY o.id_cliente, o.id_orden
) subquery
JOIN CLIENTE c ON subquery.id_cliente = c.id_cliente
GROUP BY c.username;