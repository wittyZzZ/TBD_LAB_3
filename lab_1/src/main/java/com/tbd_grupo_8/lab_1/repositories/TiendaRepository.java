package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.entities.Tienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//import org.sql2o.Connection;
//import org.sql2o.ResultSetHandler;
//import org.sql2o.Sql2o;

import java.awt.*;
import java.util.List;

@Repository
public interface TiendaRepository extends MongoRepository<Tienda, String> {
}
