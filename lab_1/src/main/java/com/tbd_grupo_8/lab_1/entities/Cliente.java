package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cliente")
public class Cliente {
    @Id
    private String id_cliente;
    private String username;
    private String direccion;
    private String email;
    private String contrasena;
    private String telefono;
    private String rol;
    private GeoJsonPoint coordenadas;
}

