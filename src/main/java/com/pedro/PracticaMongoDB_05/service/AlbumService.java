package com.pedro.PracticaMongoDB_05.service;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.Album;
import com.pedro.PracticaMongoDB_05.repository.AlbumRepository;
import com.pedro.PracticaMongoDB_05.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final GrupoRepository grupoRepository;

    public AlbumService(AlbumRepository albumRepository, GrupoRepository grupoRepository) {
        this.albumRepository = albumRepository;
        this.grupoRepository = grupoRepository;
    }

    public void crearAlbum(Album album) {
        if (!grupoRepository.existsById(album.getGrupo_id())) {
            throw new IdException("El ID del grupo no existe: " + album.getGrupo_id());
        }
        albumRepository.save(album);
    }

    public List<Album> listarAlbumes() {
        return albumRepository.findAll();
    }

    public Album listarAlbumPorId(String id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new IdException("No se encontró un álbum con el ID: " + id));
    }

    public void actualizarAlbum(String id, Album album) {
        if (!albumRepository.existsById(id)) {
            throw new IdException("No se puede actualizar, el ID del álbum no existe: " + id);
        }
        album.setId(id); // Asegurar que el ID no cambie
        albumRepository.save(album);
    }

    public void eliminarAlbum(String id) {
        if (!albumRepository.existsById(id)) {
            throw new IdException("No se puede eliminar, el ID del álbum no existe: " + id);
        }
        albumRepository.deleteById(id);
    }
}