package com.bolsadeideas.springboot.web.app.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Inscripcion implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private int idinscripcion;
    @NotNull
    @Min(1)
    @Max(9999999)
    private int idcursohabilitado;
    @NotNull
    @Min(1)
    @Max(9999999)
    private int idalumno;

    public Inscripcion() {
    }

    public Inscripcion(@NotNull int idinscripcion, @NotNull int idcursohabilitado, @NotNull int idalumno) {
        this.idinscripcion = idinscripcion;
        this.idcursohabilitado = idcursohabilitado;
        this.idalumno = idalumno;
    }

    public int getIdinscripcion() {
        return idinscripcion;
    }

    public void setIdinscripcion(int idinscripcion) {
        this.idinscripcion = idinscripcion;
    }

    public int getIdcursohabilitado() {
        return idcursohabilitado;
    }

    public void setIdcursohabilitado(int idcursohabilitado) {
        this.idcursohabilitado = idcursohabilitado;
    }

    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "idinscripcion=" + idinscripcion +
                ", idcursohabilitado=" + idcursohabilitado +
                ", idalumno=" + idalumno +
                '}'+"\n";
    }
}
