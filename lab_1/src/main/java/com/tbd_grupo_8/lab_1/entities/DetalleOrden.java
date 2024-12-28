package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrden {
    private Long id_detalle;
    private Long id_orden;
    private Long id_producto;
    private Integer cantidad;
    private Double precio_unitario;
}
