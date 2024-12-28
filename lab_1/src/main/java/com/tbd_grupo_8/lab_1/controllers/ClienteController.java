package com.tbd_grupo_8.lab_1.controllers;

import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.entities.Repartidor;
import com.tbd_grupo_8.lab_1.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Cliente> getClienteById(@PathVariable long id) {
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
    public ResponseEntity<Cliente> updateCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        cliente.setId_cliente(id);
        Cliente updatedCliente = clienteService.updateCliente(cliente);
        if (updatedCliente != null) {
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable long id) {
        Cliente deletedCliente = clienteService.deleteCliente(id);
        if (deletedCliente != null) {
            return new ResponseEntity<>(deletedCliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/inradius")
    public ResponseEntity<List<Cliente>> getClientesWithinradius(
            @RequestParam int id_tienda_input,
            @RequestParam double radius_km) {
        List<Cliente> clientes = clienteService.getClientesWithinradius(id_tienda_input,radius_km);
        return ResponseEntity.ok(clientes);
    }
}
