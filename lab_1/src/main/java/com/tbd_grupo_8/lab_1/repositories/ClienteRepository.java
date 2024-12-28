package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.entities.Repartidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    private Sql2o sql2o;

    // Obtener todos los clientes
    public List<Cliente> getAllClientes() {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT id_cliente, username, direccion, email, contrasena, telefono, rol, " +
                    "ST_X(coordenadas) AS longitude, ST_Y(coordenadas) AS latitude " +
                    "FROM cliente";
            return connection.createQuery(sql).executeAndFetch(Cliente.class);
        } catch (Exception e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
            return null;
        }
    }

    // Crear un cliente
    public Cliente createCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (username, direccion, email, contrasena, telefono, rol, coordenadas) " +
                "VALUES (:username, :direccion, :email, :contrasena, :telefono, :rol, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) " +
                "RETURNING *";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("username", cliente.getUsername())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("contrasena", cliente.getContrasena())
                    .addParameter("telefono", cliente.getTelefono())
                    .addParameter("rol", cliente.getRol())
                    .addParameter("longitude", 0)
                    .addParameter("latitude", 0)
                    .executeAndFetchFirst(Cliente.class);
        } catch (Exception e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
            return null;
        }
    }

    // Buscar cliente por username
    public Cliente findByUsername(String username) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT id_cliente, username, direccion, email, contrasena, telefono, rol, " +
                    "ST_X(coordenadas) AS longitude, ST_Y(coordenadas) AS latitude " +
                    "FROM cliente WHERE username = :username";
            return connection.createQuery(sql)
                    .addParameter("username", username)
                    .executeAndFetch(Cliente.class)
                    .get(0);
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por username: " + e.getMessage());
            return null;
        }
    }

    // Buscar cliente por email
    public Cliente findByEmail(String email) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT id_cliente, username, direccion, email, contrasena, telefono, rol, " +
                    "ST_X(coordenadas) AS longitude, ST_Y(coordenadas) AS latitude " +
                    "FROM cliente WHERE email = :email";
            return connection.createQuery(sql)
                    .addParameter("email", email)
                    .executeAndFetch(Cliente.class)
                    .get(0);
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por email: " + e.getMessage());
            return null;
        }
    }

    // Buscar cliente por ID
    public Cliente findByID(Long id) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT id_cliente, username, direccion, email, contrasena, telefono, rol, " +
                    "ST_X(coordenadas) AS longitude, ST_Y(coordenadas) AS latitude " +
                    "FROM cliente WHERE id_cliente = :id";
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Cliente.class)
                    .get(0);
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por ID: " + e.getMessage());
            return null;
        }
    }

    // Actualizar un cliente
    public Cliente updateCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET username = :username, direccion = :direccion, email = :email, contrasena = :contrasena, telefono = :telefono, rol = :rol, " +
                "coordenadas = ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326) " +
                "WHERE id_cliente = :id_cliente RETURNING *";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("username", cliente.getUsername())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("contrasena", cliente.getContrasena())
                    .addParameter("telefono", cliente.getTelefono())
                    .addParameter("rol", cliente.getRol())
                    .addParameter("longitude", cliente.getLongitude())
                    .addParameter("latitude", cliente.getLatitude())
                    .addParameter("id_cliente", cliente.getId_cliente())
                    .executeAndFetchFirst(Cliente.class);
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return null;
        }
    }

    // Eliminar un cliente
    public Cliente deleteCliente(Long id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = :id_cliente RETURNING *";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id_cliente", id)
                    .executeAndFetchFirst(Cliente.class);
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return null;
        }
    }

    public List<Cliente> getClientesWithinradius(int id_tienda_input, double radius_km) {
        String sql = "SELECT * FROM get_clientes_within_radius(:id_tienda_input, :radius_km)";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id_tienda_input", id_tienda_input)
                    .addParameter("radius_km", radius_km)
                    .executeAndFetch(Cliente.class);
        }
    }
}
