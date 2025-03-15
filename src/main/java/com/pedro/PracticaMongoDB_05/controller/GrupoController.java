package com.pedro.PracticaMongoDB_05.controller;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.Grupo;
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
@RequestMapping("/grupos") // Ruta base para todos los endpoints de este controlador
public class GrupoController {

    private final GrupoService grupoService;

    // Inyección de dependencias mediante constructor
    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    /**
     * Endpoint para crear un nuevo grupo.
     *
     * @param grupo El grupo a crear (en formato JSON en el cuerpo de la solicitud).
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PostMapping("/crear")
    public ResponseEntity<String> crearGrupo(@RequestBody Grupo grupo) {
        try {
            grupoService.crearGrupo(grupo);
            return ResponseEntity.ok().body("Grupo creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para listar todos los grupos.
     *
     * @return Respuesta HTTP con la lista de grupos o un error.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Grupo>> listarGrupos() {
        try {
            List<Grupo> grupos = grupoService.getListGrupo();
            return ResponseEntity.ok().body(grupos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Endpoint para obtener un grupo por su ID.
     *
     * @param id El ID del grupo a buscar.
     * @return Respuesta HTTP con el grupo encontrado o un error.
     */
    @GetMapping("/listar/{id}")
    public ResponseEntity<Grupo> listarGrupoPorId(@PathVariable String id) {
        try {
            Grupo grupo = grupoService.getListGrupoById(id);
            return ResponseEntity.ok().body(grupo);
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Endpoint para borrar un grupo por su ID.
     *
     * @param id El ID del grupo a borrar.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarGrupo(@PathVariable String id) {
        try {
            grupoService.deleteByIdService(id);
            return ResponseEntity.ok().body("Grupo borrado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para actualizar un grupo por su ID.
     *
     * @param id    El ID del grupo a actualizar.
     * @param grupo El grupo con los nuevos datos (en formato JSON en el cuerpo de la solicitud).
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarGrupo(@PathVariable String id, @RequestBody Grupo grupo) {
        try {
            grupoService.updateByIdService(id, grupo);
            return ResponseEntity.ok().body("Grupo actualizado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}