package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tienda")
public class Tienda {
    @Id
    private String id_tienda;
    private String nombre;
    private String direccion;
    private GeoJsonPoint coordenadas;
}
