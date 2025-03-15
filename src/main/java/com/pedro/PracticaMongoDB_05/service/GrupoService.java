package com.pedro.PracticaMongoDB_05.service;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.dto.GrupoDTO;
import com.pedro.PracticaMongoDB_05.model.entities.Grupo;
import com.pedro.PracticaMongoDB_05.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrupoService {

    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    // Metodo para crear un grupo desde un DTO
    public void crearGrupo(GrupoDTO grupoDTO) {
        Grupo grupo = fromDTO(grupoDTO);
        grupoRepository.save(grupo);
    }

    // Metodo para listar todos los grupos como DTOs
    public List<GrupoDTO> listarGrupos() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupos.stream()
                .map(this::toDTO) // Convertir cada Grupo a GrupoDTO
                .collect(Collectors.toList());
    }

    // Metodo para obtener un grupo como DTO
    public GrupoDTO obtenerGrupoDTO(String id) {
        Grupo grupo = grupoRepository.findByid(id);
        if (grupo == null) {
            throw new IdException("Este id no existe para obtener un grupo");
        }
        return toDTO(grupo);
    }

    // Metodo para actualizar un grupo desde un DTO
    public void actualizarGrupo(String id, GrupoDTO grupoDTO) {
        Grupo grupoExistente = grupoRepository.findByid(id);
        if (grupoExistente == null) {
            throw new IdException("Este id no existe para actualizar un grupo");
        }
        Grupo grupo = fromDTO(grupoDTO);
        grupo.setId(id); // Asegurar que el ID no cambie
        grupoRepository.save(grupo);
    }

    // Metodo para eliminar un grupo por ID
    public void eliminarGrupo(String id) {
        Grupo grupo = grupoRepository.findByid(id);
        if (grupo == null) {
            throw new IdException("Este id no existe para borrar un grupo");
        }
        grupoRepository.deleteById(id);
    }

    // Metodos de conversión
    private GrupoDTO toDTO(Grupo grupo) {
        GrupoDTO dto = new GrupoDTO();
        dto.setId(grupo.getId());
        dto.setNome(grupo.getNome());
        dto.setXenero(grupo.getXenero());
        dto.setDataFormacion(grupo.getDataFormacion());
        return dto;
    }

    private Grupo fromDTO(GrupoDTO dto) {
        Grupo grupo = new Grupo();
        grupo.setId(dto.getId());
        grupo.setNome(dto.getNome());
        grupo.setXenero(dto.getXenero());
        grupo.setDataFormacion(dto.getDataFormacion());
        return grupo;
    }
}