package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categoria")
public class Categoria {
    private ObjectId id_categoria;
    private String nombre;
}