package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.config.SecurityConfig;
import com.tbd_grupo_8.lab_1.entities.AuditLog;
import com.tbd_grupo_8.lab_1.entities.Producto;
import com.tbd_grupo_8.lab_1.repositories.AuditLogRepository;
import com.tbd_grupo_8.lab_1.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private AuditLogRepository auditLogRepository;
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    public Producto findById(String id) {
        return productoRepository.findById(id).orElse(null);
    }
    public void save(Producto producto) {
        productoRepository.save(producto);
        auditLogRepository.save(new AuditLog("producto", "INSERT", producto, securityConfig.getCurrentUsername(), LocalDateTime.now()));
    }
    public Producto update(Producto producto) {
        Producto existsProducto = productoRepository.findById(producto.getId_producto()).orElse(null);
        if (existsProducto != null) {
            Producto savedProducto = productoRepository.save(producto);
            auditLogRepository.save(new AuditLog("producto", "UPDATE", producto, securityConfig.getCurrentUsername(), LocalDateTime.now()));
            return savedProducto;
        }
        return null;
    }
    public boolean delete(String id) {
        if (!productoRepository.existsById(id)){
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }
}
