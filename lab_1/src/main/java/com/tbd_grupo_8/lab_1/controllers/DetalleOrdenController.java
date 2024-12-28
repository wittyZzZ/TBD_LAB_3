package com.tbd_grupo_8.lab_1.controllers;

import com.tbd_grupo_8.lab_1.entities.DetalleOrden;
import com.tbd_grupo_8.lab_1.repositories.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/detalle_orden")
public class DetalleOrdenController {
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @GetMapping("/")
    public ResponseEntity<List<DetalleOrden>> getAllDetallesOrden() {
        List<DetalleOrden> detalleOrdenes = detalleOrdenRepository.findAll();
        return new ResponseEntity<>(detalleOrdenes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrden> getDetalleOrdenById(@PathVariable long id) {
        DetalleOrden detalleOrden = detalleOrdenRepository.findById(id);
        if (detalleOrden != null) {
            return new ResponseEntity<>(detalleOrden, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<List<DetalleOrden>> createDetalleOrden(@RequestBody Map<String, Object> payload) {
        try {
            long idOrden = Long.parseLong(payload.get("id_orden").toString());
            // Convertir el carrito recibido en una lista de DetalleOrden
            List<DetalleOrden> detalles = ((List<Map<String, Object>>) payload.get("carrito")).stream()
                    .map(item -> {
                        DetalleOrden detalle = new DetalleOrden();
                        detalle.setId_orden(idOrden);
                        detalle.setId_producto(Long.parseLong(item.get("id_producto").toString()));
                        detalle.setCantidad(Integer.parseInt(item.get("cantidad").toString()));
                        detalle.setPrecio_unitario(Double.parseDouble(item.get("precio_unitario").toString()));
                        return detalle;
                    })
                    .collect(Collectors.toList());

            // Guardar los detalles en la base de datos
            List<DetalleOrden> savedDetalles = detalleOrdenRepository.save(detalles);

            return new ResponseEntity<>(savedDetalles, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleOrden> updateDetalleOrden(@PathVariable long id, @RequestBody DetalleOrden detalleOrden) {
        detalleOrden.setId_detalle(id);
        DetalleOrden savedDetalleOrden = detalleOrdenRepository.update(detalleOrden);
        if (savedDetalleOrden != null) {
            return new ResponseEntity<>(savedDetalleOrden, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleOrden> deleteDetalleOrden(@PathVariable long id) {
        if (detalleOrdenRepository.delete(id)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
