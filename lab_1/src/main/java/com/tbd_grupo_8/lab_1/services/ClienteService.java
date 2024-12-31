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
        return clienteRepository.getAllClientes();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findByID(id);
    }

    public Cliente getByUsername(String username) {
        return clienteRepository.findByUsername(username);
    }

    public Cliente getByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.createCliente(cliente);
    }

    public Cliente updateCliente(Cliente cliente) { return clienteRepository.updateCliente(cliente); }
    public Cliente deleteCliente(Long id) {
        return clienteRepository.deleteCliente(id);
    }
}
