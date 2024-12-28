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
        return zonaRepartoRepository.getAllZonas();
    }

    public ZonaReparto getById(Long id) {
        return zonaRepartoRepository.findById(id);
    }

    public ZonaReparto saveZonaReparto(ZonaReparto zonaReparto) {
        return zonaRepartoRepository.createZonaReparto(zonaReparto);
    }

    public ZonaReparto updateZonaReparto(ZonaReparto zonaReparto) { return zonaRepartoRepository.updateZonaReparto(zonaReparto); }
    public ZonaReparto deleteZonaReparto(Long id) {
        return zonaRepartoRepository.deleteZonaReparto(id);
    }

    public List<DeliveryPolygonDTO> getDeliveriesInPolygon(int id_poligono) {
        return zonaRepartoRepository.getDeliveriesInPolygon(id_poligono);
    }
}
