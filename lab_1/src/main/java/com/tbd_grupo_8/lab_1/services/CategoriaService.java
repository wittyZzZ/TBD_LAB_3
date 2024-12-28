package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.entities.Categoria;
import com.tbd_grupo_8.lab_1.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        // Verificar si la categoría existe
        Categoria existingCategoria = categoriaRepository.findById(categoria.getId_categoria());
        if (existingCategoria != null) {
            return categoriaRepository.update(categoria);
        }
        return null;
    }

    public boolean delete(Long id) {
        // Verificar si la categoría existe
        Categoria existingCategoria = categoriaRepository.findById(id);
        if (existingCategoria != null) {
            categoriaRepository.delete(id);
            return true;
        }
        return false;
    }
}