package com.pedro.PracticaMongoDB_05.repository;

import com.pedro.PracticaMongoDB_05.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la clase de albums
 *
 * @author pedro
 * @version 1.0
 */
@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {

}
