package edu.cibertec.controller.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import edu.cibertec.entity.CursoEntity;
import edu.cibertec.entity.ErrorEntity;
import edu.cibertec.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 🎓 Controlador REST para gestión de cursos.
 *
 * Exposición de endpoints bajo /api/v1/cursos
 * Soporta operaciones CRUD completas.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Curso", description = "Endpoints para la gestión de cursos")
public class CursoRestController {

    private final CursoService cursoService;

    // =====================================================
    // 🔹 Manejo centralizado de errores
    // =====================================================
    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<ErrorEntity> capturadorErrores(ResponseStatusException ex) {
        ErrorEntity error = new ErrorEntity();
        error.setStatus(ex.getStatusCode().toString());
        error.setMessage(ex.getReason());
        error.setError(ex.getStatusCode().value());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    // =====================================================
    // 🔹 Listar todos los cursos
    // =====================================================
    @Operation(summary = "Listar cursos", description = "Obtiene todos los cursos registrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<CursoEntity>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    // =====================================================
    // 🔹 Obtener curso por ID
    // =====================================================
    @Operation(summary = "Obtener curso", description = "Obtiene la información de un curso según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/{idCurso}")
    public ResponseEntity<CursoEntity> obtenerCurso(@PathVariable Integer idCurso) {
        CursoEntity curso = cursoService.obtenerCurso(idCurso);
        return (curso != null)
                ? ResponseEntity.ok(curso)
                : ResponseEntity.notFound().build();
    }

    // =====================================================
    // 🔹 Registrar nuevo curso
    // =====================================================
    @Operation(summary = "Registrar curso", description = "Crea un nuevo curso en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Curso registrado correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al registrar el curso")
    })
    @PostMapping
    public ResponseEntity<CursoEntity> registrarCurso(@RequestBody CursoEntity curso) {
        try {
            CursoEntity nuevo = cursoService.registrarCurso(curso);
            return ResponseEntity
                    .created(URI.create("/api/v1/cursos/" + nuevo.getIdCurso()))
                    .body(nuevo);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al registrar el curso",
                    ex
            );
        }
    }

    // =====================================================
    // 🔹 Actualizar curso
    // =====================================================
    @Operation(summary = "Actualizar curso", description = "Modifica la información de un curso existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @PutMapping("/{idCurso}")
    public ResponseEntity<CursoEntity> actualizarCurso(
            @PathVariable Integer idCurso,
            @RequestBody CursoEntity curso) {
        curso.setIdCurso(idCurso);
        return ResponseEntity.ok(cursoService.actualizarCurso(curso));
    }

    // =====================================================
    // 🔹 Eliminar curso
    // =====================================================
    @Operation(summary = "Eliminar curso", description = "Elimina un curso por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Curso eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer idCurso) {
        cursoService.eliminarCurso(idCurso);
        return ResponseEntity.noContent().build();
    }
}