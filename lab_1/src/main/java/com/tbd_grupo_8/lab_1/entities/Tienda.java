package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tienda {
    private Long id_tienda;
    private String nombre;
    private String direccion;
    private double latitude;
    private double longitude;
}
