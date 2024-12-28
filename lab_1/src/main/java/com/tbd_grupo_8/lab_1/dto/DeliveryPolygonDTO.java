package com.tbd_grupo_8.lab_1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DeliveryPolygonDTO {
    private int id_repartidor;
    private String nombre;
    private int id_orden;
    private String fecha_orden;
    private double latitude;
    private double longitude;
}
