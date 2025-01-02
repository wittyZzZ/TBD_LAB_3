package com.tbd_grupo_8.lab_1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "audit_log")
public class AuditLog {
    private String tabla;
    private String operacion;
    private Object datos;
    private String usuario;
    private LocalDateTime fecha;

    public AuditLog(String tabla, String operacion, Object datos, String usuario, LocalDateTime fecha) {
        this.tabla = tabla;
        this.operacion = operacion;
        this.datos = datos;
        this.usuario = usuario;
        this.fecha = fecha;
    }
}
