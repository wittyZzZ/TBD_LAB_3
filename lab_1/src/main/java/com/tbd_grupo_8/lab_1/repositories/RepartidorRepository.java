package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.dto.OrderWithinDTO;
import com.tbd_grupo_8.lab_1.entities.Repartidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepartidorRepository extends MongoRepository<Repartidor, String> {
//    // Crear un nuevo repartidor
//    public void create(Repartidor repartidor) {
//        String sql = "INSERT INTO repartidor (nombre, coordenadas) " +
//                "VALUES (:nombre, ST_SetSRID(ST_MakePoint(:longitud, :latitud), 4326))";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("nombre", repartidor.getNombre())
//                    .addParameter("longitud", repartidor.getLongitude())
//                    .addParameter("latitud", repartidor.getLatitude())
//                    .executeUpdate();
//        }
//    }
//
//    // Obtener todos los repartidores
//    public List<Repartidor> getAll() {
//        String sql = "SELECT id_repartidor, nombre, ST_X(coordenadas) AS longitude, ST_Y(coordenadas) AS latitude FROM repartidor";
//        try (Connection con = sql2o.open()) {
//            return con.createQuery(sql)
//                    .executeAndFetch((ResultSetHandler<Repartidor>) resultSet -> {
//                        Repartidor repartidor = new Repartidor();
//                        repartidor.setId_repartidor(resultSet.getLong("id_repartidor"));
//                        repartidor.setNombre(resultSet.getString("nombre"));
//                        repartidor.setLongitude(resultSet.getDouble("longitude"));
//                        repartidor.setLatitude(resultSet.getDouble("latitude"));
//                        return repartidor;
//                    });
//        } catch (Exception e) {
//            throw new RuntimeException("Error al obtener los repartidores", e);
//        }
//    }
//
//    // Obtener un repartidor por ID
//    public Repartidor getById(Long id) {
//        String sql = "SELECT id_repartidor, nombre, ST_X(coordenadas) AS longitude, ST_Y(coordenadas) AS latitude " +
//                "FROM repartidor WHERE id_repartidor = :id";
//        try (Connection con = sql2o.open()) {
//            return con.createQuery(sql)
//                    .addParameter("id", id)
//                    .executeAndFetchFirst((ResultSetHandler<Repartidor>) resultSet -> {
//                        Repartidor repartidor = new Repartidor();
//                        repartidor.setId_repartidor(resultSet.getLong("id_repartidor"));
//                        repartidor.setNombre(resultSet.getString("nombre"));
//                        repartidor.setLongitude(resultSet.getDouble("longitude"));
//                        repartidor.setLatitude(resultSet.getDouble("latitude"));
//                        return repartidor;
//                    });
//        } catch (Exception e) {
//            throw new RuntimeException("Error al obtener el repartidor por ID", e);
//        }
//    }
//
//    // Actualizar un repartidor
//    public void update(Repartidor repartidor) {
//        String sql = "UPDATE repartidor SET nombre = :nombre, " +
//                "coordenadas = ST_SetSRID(ST_MakePoint(:longitud, :latitud), 4326) " +
//                "WHERE id_repartidor = :id";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("id", repartidor.getId_repartidor())
//                    .addParameter("nombre", repartidor.getNombre())
//                    .addParameter("longitud", repartidor.getLongitude())
//                    .addParameter("latitud", repartidor.getLatitude())
//                    .executeUpdate();
//        }
//    }
//
//    // Eliminar un repartidor
//    public void delete(Long id) {
//        String sql = "DELETE FROM repartidor WHERE id_repartidor = :id";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("id", id)
//                    .executeUpdate();
//        }
//    }
//
//    public List<Repartidor> getRepartidoresWithinradius(int id_tienda_input, double radius_km) {
//        String sql = "SELECT * FROM get_repartidores_within_radius(:id_tienda_input, :radius_km)";
//        try (Connection con = sql2o.open()) {
//            return con.createQuery(sql)
//                    .addParameter("id_tienda_input", id_tienda_input)
//                    .addParameter("radius_km", radius_km)
//                    .executeAndFetch(Repartidor.class);
//        }
//    }
}
