package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.dto.OrderWithinDTO;
import com.tbd_grupo_8.lab_1.entities.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;

import java.util.List;

@Repository
public interface OrdenRepository extends MongoRepository<Orden, String> {
}
