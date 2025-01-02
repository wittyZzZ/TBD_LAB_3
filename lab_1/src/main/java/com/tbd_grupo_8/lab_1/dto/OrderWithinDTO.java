package com.tbd_grupo_8.lab_1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
public class OrderWithinDTO {
    private String id_orden;         // Cambiado de idOrden
    private String fecha_orden;  // Cambiado de fechaOrden
    private String estado;
    private int total;
    private String cliente;      // Cambiado de idCliente
    private double distancia_km; // Cambiado de distanciaKm
    private GeoJsonPoint coordenadas;
}
