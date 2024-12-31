package com.tbd_grupo_8.lab_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categoria")
public class Categoria {
    @Id
    private String id_categoria;
    private String nombre;
}