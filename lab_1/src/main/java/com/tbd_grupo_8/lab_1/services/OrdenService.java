package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.dto.OrderWithinDTO;
import com.tbd_grupo_8.lab_1.entities.Orden;
import com.tbd_grupo_8.lab_1.repositories.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    OrdenRepository ordenRepository;

    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }
    public Orden findById(long id) {
        return ordenRepository.findById(id);
    }
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
    public Orden update(Orden orden) {
        Orden existsOrden = ordenRepository.findById(orden.getId_orden());
        if (existsOrden != null) {
            return ordenRepository.update(orden);
        }
        return null;
    }
    public boolean delete(Long id) {
        Orden orden = ordenRepository.findById(id);
        if (orden != null) {
            return ordenRepository.delete(id);
        }
        return false;
    }

    public List<OrderWithinDTO> getOrdersWithinRadius(int idTienda, double radiusKm){
        return ordenRepository.getOrdersWithinRadius(idTienda, radiusKm);
    }
}
