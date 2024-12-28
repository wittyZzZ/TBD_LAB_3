package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.entities.Repartidor;
import com.tbd_grupo_8.lab_1.repositories.RepartidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class RepartidorService {

    @Autowired
    private RepartidorRepository repartidorRepository;

    public void create(Repartidor repartidor){
        repartidorRepository.create(repartidor);
    }

    public List<Repartidor> getAll(){return repartidorRepository.getAll();}

    public Repartidor getById(Long id){
        return repartidorRepository.getById(id);
    }

    public void update(Repartidor repartidor){
        repartidorRepository.update(repartidor);
    }

    public void delete(Long id){
        repartidorRepository.delete(id);
    }

    public List<Repartidor> getRepartidoresWithinradius(int id_tienda_input, double radius_km) {
        return repartidorRepository.getRepartidoresWithinradius(id_tienda_input,radius_km);
    }

}
