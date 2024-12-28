package com.tbd_grupo_8.lab_1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
public class OrderWithinDTO {
    private int id_orden;         // Cambiado de idOrden
    private String fecha_orden;  // Cambiado de fechaOrden
    private String estado;
    private double total;
    private int id_cliente;      // Cambiado de idCliente
    private double distancia_km; // Cambiado de distanciaKm
    private double latitude;
    private double longitude;
}
