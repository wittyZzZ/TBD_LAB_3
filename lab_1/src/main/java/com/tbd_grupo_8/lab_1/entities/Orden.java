package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    private Long id_orden;
    private String fecha_orden;
    private String estado;
    private Long id_cliente;
    private Double total;
    private Long id_repartidor;
    private Long id_tienda;
}
