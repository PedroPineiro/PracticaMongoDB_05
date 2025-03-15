package com.pedro.PracticaMongoDB_05.service;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.dto.AlbumDTO;
import com.pedro.PracticaMongoDB_05.model.entities.Album;
import com.pedro.PracticaMongoDB_05.model.entities.Grupo;
import com.pedro.PracticaMongoDB_05.repository.AlbumRepository;
import com.pedro.PracticaMongoDB_05.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para manejar las operaciones relacionadas con los álbumes en la base de datos MongoDB.
 * Este servicio interactúa con el repositorio de álbumes y el repositorio de grupos para realizar operaciones CRUD.
 * @author pedro
 * @version 1.0
 */
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final GrupoRepository grupoRepository;

    public AlbumService(AlbumRepository albumRepository, GrupoRepository grupoRepository) {
        this.albumRepository = albumRepository;
        this.grupoRepository = grupoRepository;
    }

    public void crearAlbum(AlbumDTO albumDTO) {
        if(!getIdGrupo(albumDTO.getGrupoID())){  // Comprobar si el grupo existe
            throw new IdException("Este id no pertenece a un grupo para ser creado");
        }
        Album album = fromDTO(albumDTO); // Convertir DTO a entidad
        albumRepository.save(album); // Guardar en la base de datos
    }

    // Metodo para listar todos los álbumes como DTO
    public List<AlbumDTO> listarAlbumes() {
        List<Album> albumes = albumRepository.findAll();
        return albumes.stream()
                .map(this::toDTO) // Convertir cada Album a AlbumDTO
                .collect(Collectors.toList());
    }

    // Metodo para obtener un álbum como DTO
    public AlbumDTO listarAlbumPorId(String id) {
        Album album = albumRepository.findByid(id);
        if (album == null) {
            throw new IdException("Este id no existe para obtener un álbum");
        }
        return toDTO(album);
    }

    // Metodo para actualizar un álbum desde un DTO
    public void actualizarAlbum(String id, AlbumDTO albumDTO) {
        Album albumExistente = albumRepository.findByid(id);
        if (albumExistente == null) {
            throw new IdException("Este id no existe para actualizar un álbum");
        }
        Album album = fromDTO(albumDTO);
        album.setId(id); // Asegurar que el ID no cambie
        albumRepository.save(album);
    }

    // Metodo para eliminar un álbum por su ID
    public void eliminarAlbum(String id) {
        Album album = albumRepository.findByid(id);
        if (album == null) {
            throw new IdException("Este id no existe para borrar un álbum");
        }
        albumRepository.deleteById(id);
    }

    // Métodos de conversión
    private AlbumDTO toDTO(Album album) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setGrupoID(album.getGrupo_id());
        dto.setTitulo(album.getTitulo());
        dto.setDataLanzamento(album.getData_lanzamento());
        dto.setPuntuacion(album.getPuntuacion());
        return dto;
    }

    private Album fromDTO(AlbumDTO dto) {
        Album album = new Album();
        album.setId(dto.getId());
        album.setGrupo_id(dto.getGrupoID());
        album.setTitulo(dto.getTitulo());
        album.setData_lanzamento(dto.getDataLanzamento());
        album.setPuntuacion(dto.getPuntuacion());
        return album;
    }

    private boolean getIdGrupo(String id){
        List<Grupo> grupoList = grupoRepository.findAll();
        for (Grupo grupo : grupoList) {
            if(id.equals(grupo.getId())){
                return true;
            }
        }
        return false;
    }
}