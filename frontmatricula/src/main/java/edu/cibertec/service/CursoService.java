package edu.cibertec.service;

import java.util.List;
import edu.cibertec.entity.CursoEntity;

/**
 * 🎓 Interfaz del servicio de gestión de cursos.
 * Define las operaciones CRUD principales que utiliza el controlador.
 */
public interface CursoService {

    List<CursoEntity> listarCursos();

    CursoEntity obtenerCurso(Integer idCurso);

    CursoEntity registrarCurso(CursoEntity curso);

    CursoEntity actualizarCurso(CursoEntity curso);

    void eliminarCurso(Integer idCurso); // ← Cambiado de CursoEntity a void
}