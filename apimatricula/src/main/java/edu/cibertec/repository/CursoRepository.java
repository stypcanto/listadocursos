package edu.cibertec.repository;

import java.util.List;

import edu.cibertec.entity.CursoEntity;

/**
 * 📘 Interfaz de acceso a datos para la entidad Curso.
 *
 * Define las operaciones básicas que cualquier implementación
 * (JDBC, JPA, REST, etc.) deberá cumplir.
 */
public interface CursoRepository {

    List<CursoEntity> listarCursos();

    CursoEntity obtenerCurso(Integer idCurso);

    CursoEntity registrarCurso(CursoEntity curso);

    CursoEntity actualizarCurso(CursoEntity curso);

    void eliminarCurso(Integer idCurso);
}