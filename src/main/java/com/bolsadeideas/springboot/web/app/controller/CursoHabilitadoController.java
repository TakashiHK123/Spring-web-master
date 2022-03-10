package com.bolsadeideas.springboot.web.app.controller;

import com.bolsadeideas.springboot.web.app.models.CursoHabilitado;
import com.bolsadeideas.springboot.web.app.models.Profesor;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cursohabilitados")
public class CursoHabilitadoController {

    @GetMapping("/listar")
    public String lista(Model model) {
        CursoHabilitadoManager cursohabilitadoManager = new CursoHabilitadoManager();
        List<CursoHabilitado> cursohabilitados = cursohabilitadoManager.getAll();
        model.addAttribute("titulo", "Lista de Curso Habilitados");
        model.addAttribute("idcursohabilitado", "ID cursohabilitado");
        model.addAttribute("idmateria", "ID Materia");
        model.addAttribute("idcurso", "ID curso");
        model.addAttribute("idprofesor", "ID Profesor");
        model.addAttribute("cursohabilitados", cursohabilitados );
        return "cursohabilitado-template/listar";
    }

    @GetMapping("/agregar")
    public String agregarCurso(Model model) {
        CursoHabilitado cursohabilitado = new CursoHabilitado();
        model.addAttribute("titulo", "Habilitar Curso");
        model.addAttribute("cursohabilitado", cursohabilitado);
        model.addAttribute("errores", new HashMap<>());
        return "cursohabilitado-template/agregar";
    }

    @PostMapping("/agregar")
    public String agregarCursoProc(@Valid CursoHabilitado cursohabilitado, BindingResult result, Model model,
                                   @RequestParam(name="idcurso") int idcurso,
                                   @RequestParam(name="idmateria") int idmateria,
                                   @RequestParam(name="idprofesor") int idprofesor) throws SQLException {
        model.addAttribute("titulo", "Falta datos");
        if(result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err ->{
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat("no puede estar vacio, y debe se numero entero"));
            });
            model.addAttribute("titulo", "Habilitar Curso");
            model.addAttribute("cursohabilitado", cursohabilitado);
            model.addAttribute("errores", errores);
            return "cursohabilitado-template/agregar";
        }
        CursoHabilitadoManager cursohabilitadoManager = new CursoHabilitadoManager();
        cursohabilitado = cursohabilitadoManager.add(idcurso, idmateria, idprofesor);
        model.addAttribute("idcursohabilitado", "ID CursoHabilitado");
        model.addAttribute("idcurso", "ID curso");
        model.addAttribute("idmateria", "ID materia");
        model.addAttribute("idprofesor", "ID profesor");
        model.addAttribute("titulo", "Habilitados");
        model.addAttribute("cursohabilitado", cursohabilitado);
        return "cursohabilitado-template/resultado";
    }
    @GetMapping("/buscar")
    public String buscarCurso(Model model) {
        CursoHabilitado cursohabilitado = new CursoHabilitado();
        model.addAttribute("titulo", "Buscar Curso Habilitado");
        model.addAttribute("cursohabilitado", cursohabilitado);
        model.addAttribute("error", new HashMap<>());
        return "cursohabilitado-template/buscar";
    }

    @PostMapping("/buscar")
    public String buscarCursoPro(@Valid CursoHabilitado cursohabilitado, BindingResult result, Model model,
                                 @RequestParam(name= "idcursohabilitado") int idcursohabilitado) throws SQLException {

        if(result.hasGlobalErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err ->{
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat("debe ser mayor a cero"));
            });
            model.addAttribute("titulo", "Debe ser mayor a cero");
            //model.addAttribute("cursohabilitado", cursohabilitado);
            model.addAttribute("error", errores);
            return "cursohabilitado-template/buscar";
        }
        CursoHabilitadoManager cursohabilitadoManager = new CursoHabilitadoManager();
        cursohabilitado = cursohabilitadoManager.getByid(idcursohabilitado);
        if(cursohabilitado==null){
            model.addAttribute("idcursohabilitado", "");
            model.addAttribute("idcurso", "");
            model.addAttribute("idmateria", " ");
            model.addAttribute("titulo", "No se encuentra habilitado o a ingresado 0");
            model.addAttribute("cursohabilitado", cursohabilitado);
        }
        else{
            model.addAttribute("idcursohabilitado", "ID Curso Habilitado");
            model.addAttribute("idcurso", "ID Curso");
            model.addAttribute("idmateria", "ID Materia");
            model.addAttribute("titulo", "El curso esta habilitado");
            model.addAttribute("cursohabilitado", cursohabilitado);
        }
        return "cursohabilitado-template/resultado";
    }
    @GetMapping("/modificar")
    public String modificarCurso(Model model) {
        CursoHabilitado cursohabilitado = new CursoHabilitado();
        model.addAttribute("titulo", "Modificar Curso Habilitado");
        model.addAttribute("cursohabilitado", cursohabilitado);
        model.addAttribute("error", new HashMap<>());
        return "cursohabilitado-template/modificar";
    }

    @PostMapping("/modificar")
    public String modificarCursoProc(@Valid CursoHabilitado cursohabilitado, BindingResult result, Model model,
                            @RequestParam(name="idcursohabilitado") int idcursohabiltado,
                            @RequestParam(name="idcurso") int idcurso,
                            @RequestParam(name="idmateria") int idmateria,
                            @RequestParam(name="idprofesor") int idprofesor ) {
        model.addAttribute("titulo", "Falta datos");
        if(result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err ->{
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat("no puede estar vacio, y debe se numero entero"));
            });
            model.addAttribute("titulo", "Modificar Curso Habilitado");
            model.addAttribute("error", errores);
            return "cursohabilitado-template/modificar";
        }
        CursoHabilitadoManager cursoHabilitadoManager = new CursoHabilitadoManager();
        cursoHabilitadoManager.modify(idcursohabiltado, idcurso, idmateria, idprofesor);
        cursohabilitado.setIdcursohabilitado(idcursohabiltado);
        cursohabilitado.setIdcurso(idcurso);
        cursohabilitado.setIdmateria(idmateria);
        cursohabilitado.setIdprofesor(idprofesor);
        model.addAttribute("idcursohabilitado", "idcursohabilitado");
        model.addAttribute("idcurso", "idcurso");
        model.addAttribute("idmateria", "idmateria");
        model.addAttribute("cursohabilitado", cursohabilitado);
        return "cursohabilitado-template/resultado";
    }

    @GetMapping("/eliminar")
    public String eliminarCurso(Model model) {
        CursoHabilitado cursohabilitado = new CursoHabilitado();
        model.addAttribute("titulo", "Buscar Curso Habilitado");
        model.addAttribute("cursohabilitado", cursohabilitado);
        model.addAttribute("error", new HashMap<>());
        return "cursohabilitado-template/eliminar";
    }

    @PostMapping("/eliminar")
    public String eliminarCursoPro(@Valid CursoHabilitado cursohabilitado, BindingResult result, Model model,
                            @RequestParam(name= "idcursohabilitado") int idcursohabilitado) throws SQLException {

        if(result.hasGlobalErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err ->{
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat("no puede estar vacio, y debe se numero entero"));
            });
            model.addAttribute("titulo", "Debe ser numero entero");
            model.addAttribute("error", errores);
            return "cursohabilitado-template/eliminar";
        }
        CursoHabilitadoManager cursohabilitadoManager = new CursoHabilitadoManager();
        cursohabilitado = cursohabilitadoManager.getByid(idcursohabilitado);
        if(cursohabilitadoManager.delete(idcursohabilitado)==false){
            model.addAttribute("idcursohabilitado", "ID Curso Habilitado");
            model.addAttribute("idcurso", "ID Curso");
            model.addAttribute("idmateria", "ID Materia");
            model.addAttribute("titulo", "El curso no se puede eliminar, esta referido a otra base de datos");
            model.addAttribute("cursohabilitado", cursohabilitado);
        }else{
            if(cursohabilitado==null){
                model.addAttribute("idcursohabilitado", " ");
                model.addAttribute("idcurso", " ");
                model.addAttribute("idmateria", " ");
                model.addAttribute("titulo", "El curso no se encuentra en la base de datos");
                model.addAttribute("cursohabilitado", cursohabilitado);
            }
            else{
                model.addAttribute("idcursohabilitado", "ID Curso Habilitado");
                model.addAttribute("idcurso", "ID Curso");
                model.addAttribute("idmateria", "ID Materia");
                model.addAttribute("titulo", "El curso esta habilitado");
                model.addAttribute("cursohabilitado", cursohabilitado);
            }
        }

        return "cursohabilitado-template/resultado";

    }
}
