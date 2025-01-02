package com.tbd_grupo_8.lab_1.dto;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class OrdenDto {
    private ObjectId id_cliente;
    private int total;
    private ObjectId id_tienda;
}
