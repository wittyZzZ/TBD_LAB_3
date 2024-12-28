package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.entities.DetalleOrden;
import com.tbd_grupo_8.lab_1.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class DetalleOrdenRepository {
    @Autowired
    private Sql2o sql2o;
    @Autowired
    private ProductoRepository productoRepository;
    public List<DetalleOrden> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id_detalle, id_orden, id_producto, cantidad, precio_unitario " +
                    "FROM detalle_orden")
                    .executeAndFetch(DetalleOrden.class);
        }
    }

    public DetalleOrden findById(long id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id_detalle, id_orden, id_producto, cantidad, precio_unitario " +
                    "FROM detalle_orden WHERE id_detalle = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DetalleOrden.class);
        }
    }

    public DetalleOrden findByOrdenId(long id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id_detalle, id_orden, id_producto, cantidad, precio_unitario " +
                            "FROM detalle_orden WHERE id_orden = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DetalleOrden.class);
        }
    }

    public List<DetalleOrden> save(List<DetalleOrden> detalleOrden) {

        String sql = "INSERT INTO detalle_orden (id_orden, id_producto, cantidad, precio_unitario) VALUES " +
                    "(:id_orden, :id_producto, :cantidad, :precio_unitario) RETURNING id_detalle";
        try (Connection conn = sql2o.beginTransaction()) {
            for (DetalleOrden detalle : detalleOrden) {

                Producto producto = productoRepository.findById(detalle.getId_producto());
                if (producto == null) {
                    throw new RuntimeException("Producto no encontrado con ID: " + detalle.getId_producto());
                }
                if (producto.getStock() < detalle.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para el producto con ID: " + detalle.getId_producto());
                }

                //Actualizar el stock de los productos comprados
                producto.setStock(producto.getStock() - detalle.getCantidad());
                productoRepository.update(producto);


                Long id = conn.createQuery(sql,true)
                        .addParameter("id_orden", detalle.getId_orden())
                        .addParameter("id_producto", detalle.getId_producto())
                        .addParameter("cantidad", detalle.getCantidad())
                        .addParameter("precio_unitario", detalle.getPrecio_unitario())
                        .executeUpdate()
                        .getKey(Long.class);
                detalle.setId_detalle(id);
            }
            conn.commit();
            return detalleOrden;
        }
    }

    public DetalleOrden update(DetalleOrden detalleOrden) {
        try (Connection conn = sql2o.open()) {
            String sql = "UPDATE detalle_orden SET " +
                    "id_orden = :id_orden," +
                    "id_producto = :id_producto," +
                    "cantidad = :cantidad," +
                    "id_orden = :id_orden," +
                    "precio_unitario = :precioUnitario" +
                    " WHERE id_detalle = :id_detalle";
            conn.createQuery(sql, true)
                    .addParameter("id_orden", detalleOrden.getId_orden())
                    .addParameter("id_producto", detalleOrden.getId_producto())
                    .addParameter("cantidad", detalleOrden.getCantidad())
                    .addParameter("precioUnitario", detalleOrden.getPrecio_unitario())
                    .executeUpdate();
            return detalleOrden;

        }
    }

    public boolean delete(Long id) {
        try (Connection conn = sql2o.open()) {
            String sql = "DELETE FROM detalle_orden WHERE id_orden = :id_orden";
            conn.createQuery(sql)
                    .addParameter("id_orden", id)
                    .executeUpdate();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
