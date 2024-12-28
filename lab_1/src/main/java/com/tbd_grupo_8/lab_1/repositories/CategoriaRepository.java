package com.tbd_grupo_8.lab_1.repositories;
import com.tbd_grupo_8.lab_1.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class CategoriaRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Categoria> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id_categoria, nombre FROM categoria")
                    .executeAndFetch(Categoria.class);
        }
    }

    public Categoria findById(Long id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id_categoria, nombre FROM categoria WHERE id_categoria = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Categoria.class);
        }
    }

    public Categoria save(Categoria categoria) {
        try (Connection conn = sql2o.open()) {
            String sql = "INSERT INTO categoria (nombre) VALUES (:nombre)";
            Long id = (Long) conn.createQuery(sql, true)
                    .addParameter("nombre", categoria.getNombre())
                    .executeUpdate()
                    .getKey();
            categoria.setId_categoria(id);
            return categoria;
        }
    }

    public Categoria update(Categoria categoria) {
        try (Connection conn = sql2o.open()) {
            String sql = "UPDATE categoria SET nombre = :nombre WHERE id_categoria = :id";
            conn.createQuery(sql)
                    .addParameter("nombre", categoria.getNombre())
                    .addParameter("id", categoria.getId_categoria())
                    .executeUpdate();
            return categoria;
        }
    }

    public void delete(Long id) {
        try (Connection conn = sql2o.open()) {
            String sql = "DELETE FROM categoria WHERE id_categoria = :id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}