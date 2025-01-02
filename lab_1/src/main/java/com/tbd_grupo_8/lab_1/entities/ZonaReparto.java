package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZonaReparto {
    private String id_zona;
    private String nombre;
    private String poligono; // Se almacena como WKT (Well-Known Text) en PostGIS
}