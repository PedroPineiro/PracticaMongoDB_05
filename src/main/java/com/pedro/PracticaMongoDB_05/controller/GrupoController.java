package com.pedro.PracticaMongoDB_05.controller;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.dto.GrupoDTO;
import com.pedro.PracticaMongoDB_05.model.entities.Grupo;
import com.pedro.PracticaMongoDB_05.service.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones CRUD sobre los documentos de "Grupo" en la base de datos MongoDB.
 * Este controlador interactúa con el servicio GrupoService para realizar operaciones de creación, lectura, actualización y eliminación (CRUD).
 * @author pedro
 * @version 1.0
 */
@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearGrupo(@RequestBody GrupoDTO grupoDTO) {
        try {
            grupoService.crearGrupo(grupoDTO);
            return ResponseEntity.ok().body("Grupo creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<GrupoDTO>> listarGrupos() {
        try {
            List<GrupoDTO> grupos = grupoService.listarGrupos();
            return ResponseEntity.ok().body(grupos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<GrupoDTO> listarGrupoPorId(@PathVariable String id) {
        try {
            GrupoDTO grupoDTO = grupoService.obtenerGrupoDTO(id);
            return ResponseEntity.ok().body(grupoDTO);
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarGrupo(@PathVariable String id, @RequestBody GrupoDTO grupoDTO) {
        try {
            grupoService.actualizarGrupo(id, grupoDTO);
            return ResponseEntity.ok().body("Grupo actualizado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarGrupo(@PathVariable String id) {
        try {
            grupoService.eliminarGrupo(id);
            return ResponseEntity.ok().body("Grupo borrado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}