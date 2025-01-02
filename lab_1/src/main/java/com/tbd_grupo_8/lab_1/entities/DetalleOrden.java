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
@Document(collection = "detalle_orden")
public class DetalleOrden {
    @Id
    private String id_detalle;
    private String id_orden;
    private String id_producto;
    private Integer cantidad;
    private int precio_unitario;
}
