package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.entities.Repartidor;
import com.tbd_grupo_8.lab_1.entities.Tienda;
import com.tbd_grupo_8.lab_1.repositories.RepartidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepartidorService {

    @Autowired
    private RepartidorRepository repartidorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void create(Repartidor repartidor){
        repartidorRepository.save(repartidor);
    }

    public List<Repartidor> getAll(){return repartidorRepository.findAll();}

    public Repartidor getById(String id){
        return repartidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repartidor not found for id: " + id));
    }

    public void update(Repartidor repartidor){
        repartidorRepository.save(repartidor);
    }

    public void delete(String id){
        repartidorRepository.deleteById(id);
    }

    public List<Repartidor> getRepartidoresWithinradius(String id_tienda_input, double radius_km) {
//        return repartidorRepository.getRepartidoresWithinradius(id_tienda_input,radius_km);
        Tienda tienda = mongoTemplate.findById(id_tienda_input, Tienda.class);
        if (tienda == null) {
            return Collections.emptyList();
        }

        // Crear la consulta geoespacial
        GeoJsonPoint tiendaPoint = tienda.getCoordenadas();
        NearQuery nearQuery = NearQuery.near(tiendaPoint)
                .maxDistance(new Distance(radius_km, Metrics.KILOMETERS));

        // Ejecutar la consulta
        GeoResults<Repartidor> results = mongoTemplate.geoNear(
                nearQuery,
                Repartidor.class
        );

        // Convertir resultados
        return results.getContent().stream()
                .map(GeoResult::getContent)
                .collect(Collectors.toList());
    }

}
