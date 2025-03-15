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

    public List<Grupo> getListGrupo() {
        return grupoRepository.findAll();
    }

    public Grupo getListGrupoById(String id) {
        return grupoRepository.findByid(id);
    }

    public void deleteByIdService(String id) {
        Grupo grupo = grupoRepository.findByid(id);
        if (grupo == null) {
            throw new IdException("Este id no existe para borrar un grupo");
        }
        grupoRepository.deleteById(id);
    }

    public void updateByIdService(String id, Grupo grupo) {
        Grupo grupoExistente = grupoRepository.findByid(id);
        if (grupoExistente == null) {
            throw new IdException("Este id no existe para actualizar un grupo");
        }
        grupoExistente.setNome(grupo.getNome());
        grupoExistente.setXenero(grupo.getXenero());
        grupoExistente.setDataFormacion(grupo.getDataFormacion());
        grupoRepository.save(grupoExistente);
    }
}