package com.pedro.PracticaMongoDB_05.controller;

import com.pedro.PracticaMongoDB_05.exceptions.IdException;
import com.pedro.PracticaMongoDB_05.model.Album;
import com.pedro.PracticaMongoDB_05.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de la clase de álbumes (endpoints para la API REST).
 * @version 1.0
 */
@RestController
@RequestMapping("/albums") // Ruta base para todos los endpoints de este controlador
public class AlbumController {

    private final AlbumService albumService;

    // Inyección de dependencias mediante constructor
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Endpoint para crear un nuevo álbum.
     *
     * @param album El álbum a crear (en formato JSON en el cuerpo de la solicitud).
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PostMapping("/crear")
    public ResponseEntity<String> crearAlbum(@RequestBody Album album) {
        try {
            albumService.crearAlbum(album);
            return ResponseEntity.ok().body("Álbum creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para listar todos los álbumes.
     *
     * @return Respuesta HTTP con la lista de álbumes o un error.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Album>> listarAlbumes() {
        try {
            List<Album> albumes = albumService.getListAlbum();
            return ResponseEntity.ok().body(albumes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Endpoint para obtener un álbum por su ID.
     *
     * @param id El ID del álbum a buscar.
     * @return Respuesta HTTP con el álbum encontrado o un error.
     */
    @GetMapping("/listar/{id}")
    public ResponseEntity<Album> listarAlbumPorId(@PathVariable String id) {
        try {
            Album album = albumService.getListAlbumById(id);
            return ResponseEntity.ok().body(album);
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Endpoint para borrar un álbum por su ID.
     *
     * @param id El ID del álbum a borrar.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarAlbum(@PathVariable String id) {
        try {
            albumService.deleteByIdService(id);
            return ResponseEntity.ok().body("Álbum borrado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para actualizar un álbum por su ID.
     *
     * @param id    El ID del álbum a actualizar.
     * @param album El álbum con los nuevos datos (en formato JSON en el cuerpo de la solicitud).
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarAlbum(@PathVariable String id, @RequestBody Album album) {
        try {
            albumService.updateByIdService(id, album);
            return ResponseEntity.ok().body("Álbum actualizado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}