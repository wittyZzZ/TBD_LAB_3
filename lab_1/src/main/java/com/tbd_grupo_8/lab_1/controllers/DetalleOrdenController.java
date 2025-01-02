package com.tbd_grupo_8.lab_1.controllers;

import com.tbd_grupo_8.lab_1.entities.DetalleOrden;
import com.tbd_grupo_8.lab_1.repositories.DetalleOrdenRepository;
import com.tbd_grupo_8.lab_1.services.DetalleOrdenService;
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
    private DetalleOrdenService detalleOrdenService;

    @GetMapping("/")
    public ResponseEntity<List<DetalleOrden>> getAllDetallesOrden() {
        List<DetalleOrden> detalleOrdenes = detalleOrdenService.findAll();
        return new ResponseEntity<>(detalleOrdenes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrden> getDetalleOrdenById(@PathVariable String id) {
        DetalleOrden detalleOrden = detalleOrdenService.findById(id);
        if (detalleOrden != null) {
            return new ResponseEntity<>(detalleOrden, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<List<DetalleOrden>> createDetalleOrden(@RequestBody Map<String, Object> payload) {
        try {
            String idOrden = payload.get("id_orden").toString();
            // Convertir el carrito recibido en una lista de DetalleOrden
            List<DetalleOrden> detalles = ((List<Map<String, Object>>) payload.get("carrito")).stream()
                    .map(item -> {
                        DetalleOrden detalle = new DetalleOrden();
                        detalle.setId_orden(idOrden);
                        detalle.setId_producto(item.get("id_producto").toString());
                        detalle.setCantidad(Integer.parseInt(item.get("cantidad").toString()));
                        detalle.setPrecio_unitario(Integer.parseInt(item.get("precio_unitario").toString()));
                        return detalle;
                    })
                    .collect(Collectors.toList());

            // Guardar los detalles en la base de datos
            List<DetalleOrden> savedDetalles = detalleOrdenService.guardarDetalles(detalles);

            return new ResponseEntity<>(savedDetalles, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleOrden> deleteDetalleOrden(@PathVariable String id) {
        if (detalleOrdenService.delete(id)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
