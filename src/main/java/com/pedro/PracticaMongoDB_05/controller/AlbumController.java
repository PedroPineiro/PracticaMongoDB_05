package com.pedro.PracticaMongoDB_05.controller;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.Album;
import com.pedro.PracticaMongoDB_05.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearAlbum(@RequestBody Album album) {
        try {
            albumService.crearAlbum(album);
            return ResponseEntity.ok().body("Álbum creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Album>> listarAlbumes() {
        try {
            List<Album> albumes = albumService.listarAlbumes();
            return ResponseEntity.ok().body(albumes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Album> listarAlbumPorId(@PathVariable String id) {
        try {
            Album album = albumService.listarAlbumPorId(id);
            return ResponseEntity.ok().body(album);
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarAlbum(@PathVariable String id, @RequestBody Album album) {
        try {
            albumService.actualizarAlbum(id, album);
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