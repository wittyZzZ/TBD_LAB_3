package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.dto.DeliveryPolygonDTO;
import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.entities.ZonaReparto;
import com.tbd_grupo_8.lab_1.repositories.ZonaRepartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaRepartoService {
    @Autowired
    private ZonaRepartoRepository zonaRepartoRepository;

    public List<ZonaReparto> getAll() {
        return zonaRepartoRepository.findAll(); //.getAllZonas();
    }

    public ZonaReparto getById(String id) {
        return zonaRepartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ZonaReparto not found for id: " + id));
    }

    public ZonaReparto saveZonaReparto(ZonaReparto zonaReparto) {
        return zonaRepartoRepository.save(zonaReparto);
    }

    public ZonaReparto updateZonaReparto(ZonaReparto zonaReparto) { return zonaRepartoRepository.save(zonaReparto); }
    public void deleteZonaReparto(String id) {
        zonaRepartoRepository.deleteById(id);
    }

    public List<DeliveryPolygonDTO> getDeliveriesInPolygon(int id_poligono) {
        return null; //zonaRepartoRepository.getDeliveriesInPolygon(id_poligono);
    }
}
