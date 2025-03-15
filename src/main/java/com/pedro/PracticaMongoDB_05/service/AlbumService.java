package com.pedro.PracticaMongoDB_05.service;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.Album;
import com.pedro.PracticaMongoDB_05.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void crearAlbum(Album album) {
        albumRepository.save(album);
    }

    public List<Album> getListAlbum() {
        return albumRepository.findAll();
    }

    public Album getListAlbumById(String id) {
        return albumRepository.findByid(id);
    }

    public void deleteByIdService(String id) {
        Album album = albumRepository.findByid(id);
        if (album == null) {
            throw new IdException("Este id no existe para borrar un álbum");
        }
        albumRepository.deleteById(id);
    }

    public void updateByIdService(String id, Album album) {
        Album albumExistente = albumRepository.findByid(id);
        if (albumExistente == null) {
            throw new IdException("Este id no existe para actualizar un álbum");
        }
        albumExistente.setTitulo(album.getTitulo());
        albumExistente.setData_lanzamento(album.getData_lanzamento());
        albumExistente.setPuntuacion(album.getPuntuacion());
        albumRepository.save(albumExistente);
    }
}