package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cliente")
public class Cliente {
    @Id
    private String id_cliente;
    private String username;
    private List<Direccion> direccion;
    private String email;
    private String contrasena;
    private String telefono;
    private String rol;
    private GeoJsonPoint coordenadas;


    public static class Direccion {
        private ObjectId id_direccion; // ObjectId as a string
        private String direccion;
        private List<Double> coordinates;

        // Getters and Setters
        public ObjectId getId_direccion() {
            return id_direccion;
        }

        public void setId_direccion(ObjectId id_direccion) {
            this.id_direccion = id_direccion;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }
}

