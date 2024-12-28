package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private long id_producto;
    private String nombre;
    private String descripcion;
    private double precio;
    private Integer stock;
    private String estado;
    private Integer id_categoria;
}
