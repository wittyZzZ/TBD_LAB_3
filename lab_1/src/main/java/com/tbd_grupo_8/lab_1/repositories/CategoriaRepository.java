package com.tbd_grupo_8.lab_1.repositories;
import com.tbd_grupo_8.lab_1.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.bson.types.ObjectId;

import java.util.List;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, ObjectId> {

}