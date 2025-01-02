package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.entities.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
}
