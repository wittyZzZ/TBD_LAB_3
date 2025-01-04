package com.tbd_grupo_8.lab_1.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tbd_grupo_8.lab_1.config.ObjectIdToStringSerializer;
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
    @JsonSerialize(using = ObjectIdToStringSerializer.class)
    private ObjectId id_orden;
    @JsonSerialize(using = ObjectIdToStringSerializer.class)
    private ObjectId id_producto;
    private Integer cantidad;
    private int precio_unitario;
}
