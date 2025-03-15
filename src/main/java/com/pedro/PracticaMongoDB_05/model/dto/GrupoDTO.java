package com.pedro.PracticaMongoDB_05.model.dto;

import java.time.LocalDate;

/**
 * Clase DTO de grupo
 *
 * @author pedro
 * @version 1.0
 */
public class GrupoDTO {

    //atributos de clase
    private String id;
    private String nome;
    private String xenero;
    private LocalDate dataFormacion;


    /**
     * Constructor de la clase
     *
     * @param id            el id del grupo
     * @param nome          el nombre del grupo
     * @param xenero        el genero del grupo
     * @param dataFormacion la fecha de formacion del grupo
     */
    public GrupoDTO(String id, String nome, String xenero, LocalDate dataFormacion) {
        this.id = id;
        this.nome = nome;
        this.xenero = xenero;
        this.dataFormacion = dataFormacion;
    }

    public GrupoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //getter y setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getXenero() {
        return xenero;
    }

    public void setXenero(String xenero) {
        this.xenero = xenero;
    }

    public LocalDate getDataFormacion() {
        return dataFormacion;
    }

    public void setDataFormacion(LocalDate dataFormacion) {
        this.dataFormacion = dataFormacion;
    }
}