package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.dto.OrderWithinDTO;
import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.entities.Orden;
import com.tbd_grupo_8.lab_1.entities.Tienda;
import com.tbd_grupo_8.lab_1.repositories.ClienteRepository;
import com.tbd_grupo_8.lab_1.repositories.OrdenRepository;
import com.tbd_grupo_8.lab_1.repositories.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    OrdenRepository ordenRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }
    public Orden findById(String id) {
        return ordenRepository.findById(id).orElse(null);
    }
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
    public Orden update(Orden orden) {
        Orden existsOrden = ordenRepository.findById(orden.getId_orden()).orElse(null);
        if (existsOrden != null) {
            return ordenRepository.save(orden);
        }
        return null;
    }
    public boolean delete(String id) {
        if (ordenRepository.existsById(id)) {
            ordenRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<OrderWithinDTO> getOrdersWithinRadius(String idTienda, double radiusKm){
        Tienda tienda = mongoTemplate.findById(idTienda, Tienda.class);
        Point tiendaLocation = tienda.getCoordenadas();
        return clienteRepository.findOrdersWithinRadius(tiendaLocation.getX(), tiendaLocation.getY(), radiusKm*1000);
    }
}
