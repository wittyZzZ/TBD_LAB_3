package com.tbd_grupo_8.lab_1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class DeliveryPolygonDTO {
    private String id_repartidor;
    private String nombre;
    private String id_orden;
    private LocalDateTime fecha_orden;
    private GeoJsonPolygon poligono;
}
