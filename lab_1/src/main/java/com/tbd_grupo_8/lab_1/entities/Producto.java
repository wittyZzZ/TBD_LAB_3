package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "producto")
public class Producto {
    @Id
    private String id_producto;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer stock;
    private String estado;
    private ObjectId id_categoria;
}
