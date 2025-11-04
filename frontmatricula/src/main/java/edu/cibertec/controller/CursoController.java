package edu.cibertec.controller;

import edu.cibertec.entity.CursoEntity;
import edu.cibertec.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 🎓 Controlador de vistas para el frontend (Thymeleaf)
 * Se comunica con el backend (API REST en localhost:8081)
 * para listar, crear, editar y eliminar cursos.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    // =====================================================
    // 🔹 Página principal (lista de cursos)
    // =====================================================
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("listaCursos", cursoService.listarCursos());
        return "mantenimientoCursos"; // nombre de la vista .html
    }

    // =====================================================
    // 🔹 Redirección raíz a /cursos
    // =====================================================
    @GetMapping("/")
    public String index() {
        return "redirect:/cursos";
    }

    // =====================================================
    // 🔹 Formulario: nuevo curso
    // =====================================================
    @GetMapping("/nuevo")
    public String nuevoCurso(Model model) {
        model.addAttribute("curso", new CursoEntity());
        return "nuevoCurso";
    }

    // =====================================================
    // 🔹 Guardar curso
    // =====================================================
    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute("curso") CursoEntity curso) {
        cursoService.registrarCurso(curso);
        return "redirect:/cursos";
    }

    // =====================================================
    // 🔹 Editar curso
    // =====================================================
    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable("id") Integer id, Model model) {
        CursoEntity curso = cursoService.obtenerCurso(id);
        if (curso == null) {
            return "redirect:/cursos";
        }
        model.addAttribute("curso", curso);
        return "nuevoCurso";
    }

    // =====================================================
    // 🔹 Eliminar curso
    // =====================================================
    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable("id") Integer id) {
        cursoService.eliminarCurso(id);
        return "redirect:/cursos";
    }
}