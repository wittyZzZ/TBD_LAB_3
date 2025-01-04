package com.tbd_grupo_8.lab_1.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdToStringSerializer extends JsonSerializer<ObjectId> {
    @Override
    public void serialize(ObjectId objectId, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (objectId != null) {
            gen.writeString(objectId.toString()); // Converts ObjectId to a plain string
        } else {
            gen.writeNull();
        }
    }
}

