package com.tbd_grupo_8.lab_1.controllers;

import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.services.ClienteService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> listClientes() {
        List<Cliente> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable String id) {
        Cliente cliente = clienteService.getById(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente createdCliente = clienteService.saveCliente(cliente);
            return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        cliente.setId_cliente(id);
        Cliente updatedCliente = clienteService.updateCliente(cliente);
        if (updatedCliente != null) {
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable String id) {
        clienteService.deleteCliente(id);
        Cliente deletedCliente = clienteService.getById(id);
        if (deletedCliente != null) {
            return new ResponseEntity<>(deletedCliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/direccion")
    public ResponseEntity<Cliente> addDireccion(@PathVariable String id, @RequestBody Cliente.Direccion direccion) {
        Cliente cliente = clienteService.getById(id);
        if (cliente == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        direccion.setId_direccion(new ObjectId());
        cliente.getDireccion().add(direccion);
        clienteService.saveCliente(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}/direccion/{direccionId}/main")
    public ResponseEntity<Cliente> setMainDireccion(@PathVariable String id, @PathVariable String direccionId) {
        Cliente cliente = clienteService.getById(id);
        if (cliente == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Cliente.Direccion direccion : cliente.getDireccion()) {
            if (direccion.getId_direccion().toString().equals(direccionId)) {
                cliente.setCoordenadas(new GeoJsonPoint(
                        direccion.getCoordinates().get(0),
                        direccion.getCoordinates().get(1)
                ));
                clienteService.saveCliente(cliente);
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{clienteId}/direccion/{direccionId}")
    public ResponseEntity<String> deleteDireccion(
            @PathVariable String clienteId,
            @PathVariable String direccionId) {
        Cliente cliente = clienteService.getById(clienteId);
        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        Cliente.Direccion direccionToDelete = cliente.getDireccion().stream()
                .filter(direccion -> direccion.getId_direccion().toString().equals(direccionId))
                .findFirst()
                .orElse(null);

        if (cliente.getCoordenadas() != null &&
                cliente.getCoordenadas().getCoordinates().equals(direccionToDelete.getCoordinates())) {
            return new ResponseEntity<>(
                    "No se puede eliminar la dirección principal, se necesita colocar otra dirección como la principal",
                    HttpStatus.BAD_REQUEST);
        }

        cliente.getDireccion().remove(direccionToDelete);

        clienteService.saveCliente(cliente); // Save the updated cliente
        return new ResponseEntity<>("Dirección eliminada", HttpStatus.OK);
    }
}
