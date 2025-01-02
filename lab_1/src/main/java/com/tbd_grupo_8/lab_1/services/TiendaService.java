package com.tbd_grupo_8.lab_1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tbd_grupo_8.lab_1.entities.Tienda;
import com.tbd_grupo_8.lab_1.repositories.TiendaRepository;

import java.util.List;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    public void create(Tienda tienda) {
        tiendaRepository.save(tienda);
    }

    public List<Tienda> getAll() {
        return tiendaRepository.findAll();
    }

    public Tienda getById(String id) {
        return tiendaRepository.findById(id).orElse(null);
    }

    public void update(Tienda tienda) {
        tiendaRepository.save(tienda);
    }

    public void delete(String id) {
        tiendaRepository.deleteById(id);
    }
}