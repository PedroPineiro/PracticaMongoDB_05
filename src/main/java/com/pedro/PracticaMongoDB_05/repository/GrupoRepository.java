package com.pedro.PracticaMongoDB_05.repository;

import com.pedro.PracticaMongoDB_05.model.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la clase de grupos
 *
 * @author pedro
 * @version 1.0
 */
@Repository
public interface GrupoRepository extends MongoRepository<Grupo, String> {

}
