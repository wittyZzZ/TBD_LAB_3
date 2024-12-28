package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.config.SecurityConfig;
import com.tbd_grupo_8.lab_1.entities.Categoria;
import com.tbd_grupo_8.lab_1.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class ProductoRepository {
    @Autowired
    private Sql2o sql2o;
    @Autowired
    private SecurityConfig securityConfig;

    public List<Producto> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id_producto, id_producto, nombre, descripcion, precio, stock," +
                            " estado, id_categoria FROM producto")
                    .executeAndFetch(Producto.class);
        }
    }
    public Producto findById(long id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id_producto, nombre, descripcion, precio, stock, estado, " +
                            "id_categoria FROM producto WHERE id_producto = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Producto.class);
        }
    }
    public void save(Producto producto) {
        String sql = "INSERT INTO \"producto\" (nombre, descripcion, precio, stock, estado, id_categoria) VALUES (:nombre, :descripcion, :precio, :stock, :estado, :id_categoria)";
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("descripcion", producto.getDescripcion())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("stock", producto.getStock())
                    .addParameter("estado", producto.getEstado())
                    .addParameter("id_categoria", producto.getId_categoria())
                    .executeUpdate();
        }
    }
    public Producto update(Producto producto) {
        try (Connection conn = sql2o.beginTransaction()) {
            String setUserSql = "SELECT set_config('myapp.current_user', :username, false)";
            conn.createQuery(setUserSql)
                    .addParameter("username", securityConfig.getCurrentUsername())
                    .executeAndFetchFirst(String.class);


            String sql = "UPDATE producto SET nombre=:nombre, descripcion=:descripcion, precio=:precio, " +
                    "stock=:stock, estado=:estado, id_categoria=:id_categoria WHERE id_producto = :id_producto";
            conn.createQuery(sql, true)
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("descripcion", producto.getDescripcion())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("stock", producto.getStock())
                    .addParameter("estado", producto.getEstado())
                    .addParameter("id_categoria", producto.getId_categoria())
                    .addParameter("id_producto", producto.getId_producto())
                    .executeUpdate();
            conn.commit();
            return producto;
        }
    }
    public boolean delete(long id) {
        try (Connection conn = sql2o.beginTransaction()) {
            String setUserSql = "SELECT set_config('myapp.current_user', :username, false)";
            conn.createQuery(setUserSql)
                    .addParameter("username", securityConfig.getCurrentUsername())
                    .executeAndFetchFirst(String.class);

            String sql = "DELETE FROM producto WHERE id_producto = :id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            conn.commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
