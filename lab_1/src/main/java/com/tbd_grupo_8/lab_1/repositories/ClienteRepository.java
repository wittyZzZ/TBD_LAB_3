package com.tbd_grupo_8.lab_1.repositories;

import com.tbd_grupo_8.lab_1.dto.OrderWithinDTO;
import com.tbd_grupo_8.lab_1.entities.Categoria;
import com.tbd_grupo_8.lab_1.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

    Cliente findByUsername(String username);
    Cliente findByEmail(String email);

    @Aggregation(pipeline = {
            "{ $geoNear: { near: { type: 'Point', coordinates: [ ?0, ?1 ] }, distanceField: 'distancia_km', maxDistance: ?2, spherical: true } }",
            "{ $lookup: { from: 'orden', localField: '_id', foreignField: 'id_cliente', as: 'ordenes_cercanas' } }",
            "{ $unwind: '$ordenes_cercanas' }",
            "{ $project: { id_orden: '$ordenes_cercanas._id', fecha_orden: '$ordenes_cercanas.fecha_orden', estado: '$ordenes_cercanas.estado', total: '$ordenes_cercanas.total', cliente: '$ordenes_cercanas.id_cliente', distancia_km: { $divide: ['$distancia_km', 1000.0] }, coordenadas: '$coordenadas' } }"
    })


    List<OrderWithinDTO> findOrdersWithinRadius(double latitude, double longitude, double radiusKm);
}
