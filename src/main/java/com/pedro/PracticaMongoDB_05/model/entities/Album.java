package com.pedro.PracticaMongoDB_05.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Entidad de albumes con el documento
 * @author pedro
 * @version 1.0
 */
@Document(collection = "albums")
public class Album {

    //clave primaria
    @Id
    private String id;

    //atributos de clase
    private String grupo_id;
    private String titulo;
    private LocalDate data_lanzamento;
    private float puntuacion;

    /**
     * Constructor de la clase
     * @param id la clave primaria
     * @param grupo_id el id del grupo al que pertenece el album
     * @param titulo el titulo del album
     * @param data_lanzamento la fecha de lanzamiento del album
     * @param puntuacion la puntuacion del album
     */
    public Album(String id, String grupo_id, String titulo, LocalDate data_lanzamento, float puntuacion) {
        this.id = id;
        this.grupo_id = grupo_id;
        this.titulo = titulo;
        this.data_lanzamento = data_lanzamento;
        this.puntuacion = puntuacion;
    }

    public Album(String grupo_id, String titulo, LocalDate data_lanzamento, float puntuacion) {
        this.grupo_id = grupo_id;
        this.titulo = titulo;
        this.data_lanzamento = data_lanzamento;
        this.puntuacion = puntuacion;
    }

    public Album() {

    }

    //getter y setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(String grupo_id) {
        this.grupo_id = grupo_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData_lanzamento() {
        return data_lanzamento;
    }

    public void setData_lanzamento(LocalDate data_lanzamento) {
        this.data_lanzamento = data_lanzamento;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        // Redondear a un decimal
        this.puntuacion = (float) (Math.round(puntuacion * 10) / 10.0);
    }
}
