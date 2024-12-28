package com.tbd_grupo_8.lab_1.dto;

import lombok.Data;

@Data
public class OrdenDto {
    private Long id_cliente;
    private Double total;
    private Long id_tienda;
}
