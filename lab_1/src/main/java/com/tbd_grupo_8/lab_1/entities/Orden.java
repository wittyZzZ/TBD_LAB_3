package com.tbd_grupo_8.lab_1.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tbd_grupo_8.lab_1.config.ObjectIdToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orden")
public class Orden {
    @Id
    private String id_orden;
    private LocalDateTime fecha_orden;
    private String estado;
    @JsonSerialize(using = ObjectIdToStringSerializer.class)
    private ObjectId id_cliente;
    private int total;
    @JsonSerialize(using = ObjectIdToStringSerializer.class)
    private ObjectId id_repartidor;
    @JsonSerialize(using = ObjectIdToStringSerializer.class)
    private ObjectId id_tienda;
}
