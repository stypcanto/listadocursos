package edu.cibertec.repository;

import java.util.List;
import edu.cibertec.entity.CursoEntity;

/**
 * 🎯 Repositorio de acceso al API de Cursos.
 * Encapsula las operaciones CRUD contra el backend REST (apimatricula).
 */
public interface CursoRepository {

    /** 🔍 Listar todos los cursos disponibles. */
    List<CursoEntity> listarCursos();

    /** 🔍 Obtener un curso por su ID. */
    CursoEntity obtenerCurso(Integer idCurso);

    /** ➕ Registrar un nuevo curso. */
    CursoEntity registrarCurso(CursoEntity curso);

    /** ✏️ Actualizar un curso existente. */
    CursoEntity actualizarCurso(CursoEntity curso);

    /** ❌ Eliminar un curso por su ID. */
    void eliminarCurso(Integer idCurso);
}