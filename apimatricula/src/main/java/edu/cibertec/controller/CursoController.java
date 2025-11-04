package edu.cibertec.controller;

import edu.cibertec.entity.CursoEntity;
import edu.cibertec.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    // =====================================================
    // 🔹 Vista principal: lista de cursos
    // =====================================================
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("listaCursos", cursoService.listarCursos());
        return "mantenimientoCursos"; // archivo HTML en templates/
    }

    // =====================================================
    // 🔹 Mostrar formulario de nuevo curso
    // =====================================================
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("curso", new CursoEntity());
        return "nuevoCurso"; // archivo HTML en templates/
    }

    // =====================================================
    // 🔹 Guardar nuevo curso (POST)
    // =====================================================
    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute CursoEntity curso) {
        cursoService.registrarCurso(curso);
        return "redirect:/cursos";
    }

    // =====================================================
    // 🔹 Eliminar curso por ID (GET o POST)
    // =====================================================
    @GetMapping("/eliminar/{idCurso}")
    public String eliminarCurso(@PathVariable Integer idCurso) {
        cursoService.eliminarCurso(idCurso);
        return "redirect:/cursos";
    }

    // =====================================================
    // 🔹 Editar curso (opcional)
    // =====================================================
    @GetMapping("/editar/{idCurso}")
    public String mostrarFormularioEditar(@PathVariable Integer idCurso, Model model) {
        CursoEntity curso = cursoService.obtenerCurso(idCurso);
        model.addAttribute("curso", curso);
        return "nuevoCurso"; // reutiliza el mismo formulario
    }
}