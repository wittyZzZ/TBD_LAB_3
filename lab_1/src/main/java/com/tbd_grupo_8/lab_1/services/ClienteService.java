package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getById(String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente getByUsername(String username) {
        return clienteRepository.findByUsername(username);
    }

    public Cliente getByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Cliente cliente) { return clienteRepository.save(cliente); }
    public void deleteCliente(String id) {
        clienteRepository.deleteById(id);
    }
}
