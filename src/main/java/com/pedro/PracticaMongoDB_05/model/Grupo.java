package com.pedro.PracticaMongoDB_05.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Entidad de grupos con el documento
 * @author pedro
 * @version 1.0
 */
@Document(collection = "grupos")
public class Grupo {

    //clave primaria
    @Id
    private String id;

    //atributos de clase
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("xenero")
    private String xenero;

    @JsonProperty("dataFormacion")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFormacion;

    /**
     * Constructor de la clase
     * @param id la clave primaria
     * @param nome el nombre del grupo
     * @param xenero el genero del grupo
     * @param dataFormacion la fecha de formacion del grupo
     */
    public Grupo(String id, String nome, String xenero, LocalDate dataFormacion) {
        this.id = id;
        this.nome = nome;
        this.xenero = xenero;
        this.dataFormacion = dataFormacion;
    }

    public Grupo(String nome, String xenero, LocalDate dataFormacion) {
        this.nome = nome;
        this.xenero = xenero;
        this.dataFormacion = dataFormacion;
    }

    public Grupo(){

    }

    //getter y setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
