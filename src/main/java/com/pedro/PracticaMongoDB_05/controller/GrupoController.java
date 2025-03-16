package com.pedro.PracticaMongoDB_05.controller;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.Grupo;
import com.pedro.PracticaMongoDB_05.service.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearGrupo(@RequestBody Grupo grupo) {
        try {
            grupoService.crearGrupo(grupo);
            return ResponseEntity.ok().body("Grupo creado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Grupo>> listarGrupos() {
        try {
            List<Grupo> grupos = grupoService.listarGrupos();
            return ResponseEntity.ok().body(grupos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Grupo> listarGrupoPorId(@PathVariable String id) {
        try {
            Grupo grupo = grupoService.obtenerGrupo(id);
            return ResponseEntity.ok().body(grupo);
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarGrupo(@PathVariable String id, @RequestBody Grupo grupo) {
        try {
            grupoService.actualizarGrupo(id, grupo);
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