package com.tbd_grupo_8.lab_1.controllers;

import com.tbd_grupo_8.lab_1.dto.DeliveryPolygonDTO;
import com.tbd_grupo_8.lab_1.entities.ZonaReparto;
import com.tbd_grupo_8.lab_1.services.ZonaRepartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zona-reparto")
public class ZonaRepartoController {

    @Autowired
    private ZonaRepartoService zonaRepartoService;

    // Obtener todas las zonas de reparto
    @GetMapping("/")
    public ResponseEntity<List<ZonaReparto>> listZonasReparto() {
        List<ZonaReparto> zonas = zonaRepartoService.getAll();
        return ResponseEntity.ok(zonas);
    }

    // Obtener una zona de reparto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ZonaReparto> getZonaRepartoById(@PathVariable String id) {
        ZonaReparto zona = zonaRepartoService.getById(id);
        if (zona != null) {
            return new ResponseEntity<>(zona, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Crear una nueva zona de reparto
    @PostMapping("/")
    public ResponseEntity<ZonaReparto> createZonaReparto(@RequestBody ZonaReparto zonaReparto) {
        try {
            ZonaReparto createdZona = zonaRepartoService.saveZonaReparto(zonaReparto);
            return new ResponseEntity<>(createdZona, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una zona de reparto existente
    @PutMapping("/{id}")
    public ResponseEntity<ZonaReparto> updateZonaReparto(@PathVariable String id, @RequestBody ZonaReparto zonaReparto) {
        zonaReparto.setId_zona(id);
        ZonaReparto updatedZona = zonaRepartoService.updateZonaReparto(zonaReparto);
        if (updatedZona != null) {
            return new ResponseEntity<>(updatedZona, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Eliminar una zona de reparto
    @DeleteMapping("/{id}")
    public ResponseEntity<ZonaReparto> deleteZonaReparto(@PathVariable String id) {
//        ZonaReparto deletedZona = zonaRepartoService.deleteZonaReparto(id);
//        if (deletedZona != null) {
//            return new ResponseEntity<>(deletedZona, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        zonaRepartoService.deleteZonaReparto(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/deliveryinpolygon")
    public ResponseEntity<List<DeliveryPolygonDTO>> getDeliveriesInPolygon(@RequestParam String id_poligono) {
        List<DeliveryPolygonDTO> result = zonaRepartoService.getDeliveriesInPolygon(id_poligono);
        return ResponseEntity.ok(result);
    }
}
