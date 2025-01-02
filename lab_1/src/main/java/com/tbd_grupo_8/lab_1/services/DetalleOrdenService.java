package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.entities.DetalleOrden;
import com.tbd_grupo_8.lab_1.entities.Producto;
import com.tbd_grupo_8.lab_1.repositories.DetalleOrdenRepository;
import com.tbd_grupo_8.lab_1.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOrdenService {
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;
    @Autowired
    private ProductoRepository productoRepository;
    public List<DetalleOrden> findAll() { return detalleOrdenRepository.findAll(); }
    public DetalleOrden findById(String id) { return detalleOrdenRepository.findById(id).orElse(null); }
//    public DetalleOrden findByOrdenId(String id) { return detalleOrdenRepository.findById_orden(id); }
    public List<DetalleOrden> guardarDetalles(List<DetalleOrden> detalleOrdenList) {

        //En vez de hacer un saveAll, se hace uno por uno para poder modificar los stocks
        for (DetalleOrden detalle : detalleOrdenList) {

            // Verificar si el producto existe
            Producto producto = productoRepository.findById(detalle.getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalle.getId_producto()));

            // Verificar si hay suficiente stock
            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto con ID: " + detalle.getId_producto());
            }

            // Actualizar el stock del producto
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto); // Actualiza el stock en la base de datos

            // Guardar el detalle de la orden
            detalleOrdenRepository.save(detalle); // MongoDB generará automáticamente el ID
        }
        return detalleOrdenList;
    }

    public boolean delete(String id) {
        if (detalleOrdenRepository.existsById(id)) {
            detalleOrdenRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
