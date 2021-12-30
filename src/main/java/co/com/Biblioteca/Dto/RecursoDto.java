package co.com.Biblioteca.Dto;

import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;

import java.util.Date;

public class RecursoDto {

    private String id;
    private String nombre;
    private boolean Disponible = true;
    private Date fechaPrestamo;
    private AreaTematica areaTematica;
    private TipoRecurso tipoRecurso;

    public RecursoDto() {
    }

    public RecursoDto(String nombre, AreaTematica areaTematica, TipoRecurso tipoRecurso) {
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        this.Disponible = disponible;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
}
