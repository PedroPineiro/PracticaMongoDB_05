package com.pedro.PracticaMongoDB_05.controller;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.dto.AlbumDTO;
import com.pedro.PracticaMongoDB_05.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de la clase de álbumes (endpoints para la API REST).
 * @version 1.0
 */
@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearAlbum(@RequestBody AlbumDTO albumDTO) {
        try {
            albumService.crearAlbum(albumDTO);
            return ResponseEntity.ok().body("Álbum creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AlbumDTO>> listarAlbumes() {
        try {
            List<AlbumDTO> albumes = albumService.listarAlbumes();
            return ResponseEntity.ok().body(albumes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<AlbumDTO> listarAlbumPorId(@PathVariable String id) {
        try {
            AlbumDTO albumDTO = albumService.listarAlbumPorId(id);
            return ResponseEntity.ok().body(albumDTO);
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarAlbum(@PathVariable String id, @RequestBody AlbumDTO albumDTO) {
        try {
            albumService.actualizarAlbum(id, albumDTO);
            return ResponseEntity.ok().body("Álbum actualizado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarAlbum(@PathVariable String id) {
        try {
            albumService.eliminarAlbum(id);
            return ResponseEntity.ok().body("Álbum borrado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}