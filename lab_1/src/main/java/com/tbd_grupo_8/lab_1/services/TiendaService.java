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
        tiendaRepository.create(tienda);
    }

    public List<Tienda> getAll() {
        return tiendaRepository.getAll();
    }

    public Tienda getById(Long id) {
        return tiendaRepository.getById(id);
    }

    public void update(Tienda tienda) {
        tiendaRepository.update(tienda);
    }

    public void delete(Long id) {
        tiendaRepository.delete(id);
    }
}