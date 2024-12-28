package com.tbd_grupo_8.lab_1.repositories;
import com.tbd_grupo_8.lab_1.dto.DeliveryPolygonDTO;
import com.tbd_grupo_8.lab_1.dto.OrderWithinDTO;
import com.tbd_grupo_8.lab_1.entities.ZonaReparto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class ZonaRepartoRepository {

    @Autowired
    private Sql2o sql2o;

    // Obtener todas las zonas de reparto
    public List<ZonaReparto> getAllZonas() {
        String sql = "SELECT id_zona, nombre, ST_AsText(poligono) AS poligono FROM zona_reparto";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(ZonaReparto.class);
        } catch (Exception e) {
            System.out.println("Error al obtener zonas de reparto: " + e.getMessage());
            return null;
        }
    }

    // Crear una nueva zona de reparto
    public ZonaReparto createZonaReparto(ZonaReparto zonaReparto) {
        String sql = "INSERT INTO zona_reparto (nombre, poligono) " +
                "VALUES (:nombre, ST_GeomFromText(:poligono, 4326)) " +
                "RETURNING id_zona, nombre, ST_AsText(poligono) AS poligono";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("nombre", zonaReparto.getNombre())
                    .addParameter("poligono", zonaReparto.getPoligono())
                    .executeAndFetchFirst(ZonaReparto.class);
        } catch (Exception e) {
            System.out.println("Error al crear zona de reparto: " + e.getMessage());
            return null;
        }
    }

    // Buscar zona de reparto por ID
    public ZonaReparto findById(Long id) {
        String sql = "SELECT id_zona, nombre, ST_AsText(poligono) AS poligono " +
                "FROM zona_reparto WHERE id_zona = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(ZonaReparto.class);
        } catch (Exception e) {
            System.out.println("Error al buscar zona de reparto por ID: " + e.getMessage());
            return null;
        }
    }

    // Actualizar una zona de reparto
    public ZonaReparto updateZonaReparto(ZonaReparto zonaReparto) {
        String sql = "UPDATE zona_reparto SET nombre = :nombre, poligono = ST_GeomFromText(:poligono, 4326) " +
                "WHERE id_zona = :id_zona RETURNING id_zona, nombre, ST_AsText(poligono) AS poligono";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("nombre", zonaReparto.getNombre())
                    .addParameter("poligono", zonaReparto.getPoligono())
                    .addParameter("id_zona", zonaReparto.getId_zona())
                    .executeAndFetchFirst(ZonaReparto.class);
        } catch (Exception e) {
            System.out.println("Error al actualizar zona de reparto: " + e.getMessage());
            return null;
        }
    }

    // Eliminar una zona de reparto
    public ZonaReparto deleteZonaReparto(Long id) {
        String sql = "DELETE FROM zona_reparto WHERE id_zona = :id_zona RETURNING id_zona, nombre, ST_AsText(poligono) AS poligono";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id_zona", id)
                    .executeAndFetchFirst(ZonaReparto.class);
        } catch (Exception e) {
            System.out.println("Error al eliminar zona de reparto: " + e.getMessage());
            return null;
        }
    }

    public List<DeliveryPolygonDTO> getDeliveriesInPolygon(int id_poligono) {
        String sql = "SELECT * FROM get_delivery_in_polygon(:id_poligono)";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id_poligono", id_poligono)
                    .executeAndFetch(DeliveryPolygonDTO.class);
        }
    }
}

