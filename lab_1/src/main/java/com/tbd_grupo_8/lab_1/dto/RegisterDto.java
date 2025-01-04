package com.tbd_grupo_8.lab_1.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private List<Double> coordinates;
    private String rol;
}
