\c tbd_lab_1

-- Actualizar el stock a 0
UPDATE PRODUCTO SET stock = 0 WHERE id_producto = 1;

-- Verificar el cambio
SELECT id_producto, nombre, stock, estado FROM PRODUCTO WHERE id_producto = 1;

-- Actualizar el stock a un número positivo
UPDATE PRODUCTO SET stock = 5 WHERE id_producto = 1;

-- Verificar que volvió a 'Disponible'
SELECT id_producto, nombre, stock, estado FROM PRODUCTO WHERE id_producto = 1;

-- Actualizar el precio de un producto
UPDATE PRODUCTO SET precio = 5000 WHERE id_producto = 1;
--Se llama el procedimiento almacenado
CALL reporte_audit();

--Debido al trigger que ve por cambios en el precio, esa última query se realizará dos veces, mostrando que el trigger
--para los queries de manera general se aplica igualmente junto con el otro trigger específico