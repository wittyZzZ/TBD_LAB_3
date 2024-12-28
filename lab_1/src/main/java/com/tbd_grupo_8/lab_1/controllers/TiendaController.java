package com.tbd_grupo_8.lab_1.controllers;
import com.tbd_grupo_8.lab_1.entities.Tienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tbd_grupo_8.lab_1.services.TiendaService;
import java.util.List;


@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

    @Autowired
    TiendaService tiendaService;

    @GetMapping("/")
    public ResponseEntity<List<Tienda>> listTiendas() {
        List<Tienda> tiendas = tiendaService.getAll();
        return ResponseEntity.ok(tiendas);
    }

    @PostMapping("/")
    public ResponseEntity<Tienda> saveTienda(@RequestBody Tienda tienda) {
        tiendaService.create(tienda);
        return ResponseEntity.ok(tienda);
    }

    @PutMapping("/")
    public ResponseEntity<Tienda> updateTienda(@RequestBody Tienda tienda) {
        tiendaService.update(tienda);
        return ResponseEntity.ok(tienda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> getTienda(@PathVariable Long id) {
        Tienda tienda = tiendaService.getById(id);
        return ResponseEntity.ok(tienda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTienda(@PathVariable Long id) {
        tiendaService.delete(id);
        return ResponseEntity.ok("Tienda eliminada");
    }
}
