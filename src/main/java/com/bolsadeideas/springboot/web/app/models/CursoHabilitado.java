package com.bolsadeideas.springboot.web.app.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class CursoHabilitado implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    private int idcursohabilitado;
    @NotNull
    @Min(1)
    @Max(9999999)
    private int idcurso;
    @NotNull
    @Min(1)
    @Max(9999999)
    private int idmateria;
    @NotNull
    @Min(1)
    @Max(9999999)
    private int idprofesor;

    public CursoHabilitado() {
    }

    public CursoHabilitado(@NotNull int idcursohabilitado, @NotNull int idcurso, @NotNull int idmateria, @NotNull int idprofesor) {
        this.idcursohabilitado = idcursohabilitado;
        this.idcurso = idcurso;
        this.idmateria = idmateria;
        this.idprofesor = idprofesor;
    }

    public int getIdcursohabilitado() {
        return idcursohabilitado;
    }

    public void setIdcursohabilitado(int idcursohabilitado) {
        this.idcursohabilitado = idcursohabilitado;
    }

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public int getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(int idmateria) {
        this.idmateria = idmateria;
    }

    public int getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }

    @Override
    public String toString() {
        return "CursoHabilitado{" +
                "idcursohabilitado=" + idcursohabilitado +
                ", idcurso=" + idcurso +
                ", idmateria=" + idmateria +
                ", idprofesor=" + idprofesor +
                '}'+"\n";
    }
}
