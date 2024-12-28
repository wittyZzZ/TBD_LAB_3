package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long id_cliente;
    private String username;
    private String direccion;
    private String email;
    private String contrasena;
    private String telefono;
    private String rol;
    private double latitude;
    private double longitude;
}
