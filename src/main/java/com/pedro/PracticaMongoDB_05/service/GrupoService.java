package com.pedro.PracticaMongoDB_05.service;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.Grupo;
import com.pedro.PracticaMongoDB_05.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    public void crearGrupo(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    public List<Grupo> listarGrupos() {
        return grupoRepository.findAll();
    }

    public Grupo obtenerGrupo(String id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new IdException("No se encontró un grupo con el ID: " + id));
    }

    public void actualizarGrupo(String id, Grupo grupo) {
        if (!grupoRepository.existsById(id)) {
            throw new IdException("No se puede actualizar, el ID del grupo no existe: " + id);
        }
        grupo.setId(id); // Asegurar que el ID no cambie
        grupoRepository.save(grupo);
    }

    public void eliminarGrupo(String id) {
        if (!grupoRepository.existsById(id)) {
            throw new IdException("No se puede eliminar, el ID del grupo no existe: " + id);
        }
        grupoRepository.deleteById(id);
    }
}