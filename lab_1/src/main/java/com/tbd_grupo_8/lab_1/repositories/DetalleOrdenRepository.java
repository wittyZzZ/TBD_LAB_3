package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.entities.DetalleOrden;
import com.tbd_grupo_8.lab_1.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;

import java.util.List;

@Repository
public interface DetalleOrdenRepository extends MongoRepository<DetalleOrden, String> {
//    DetalleOrden findById_orden(String id_orden);
//    List<DetalleOrden> guardarDetalles(List<DetalleOrden> detalleOrdenList);
}
