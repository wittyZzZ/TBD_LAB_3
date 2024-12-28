package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.entities.Producto;
import com.tbd_grupo_8.lab_1.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    public Producto findById(long id) {
        return productoRepository.findById(id);
    }
    public void save(Producto producto) {
        productoRepository.save(producto);
    }
    public Producto update(Producto producto) {
        Producto existsProducto = productoRepository.findById(producto.getId_producto());
        if (existsProducto != null) {
            return productoRepository.update(producto);
        }
        return null;
    }
    public boolean delete(Long id) {
        Producto existsProducto = productoRepository.findById(id);
        if (existsProducto != null) {
            return productoRepository.delete(id);
        }
        return false;
    }
}
