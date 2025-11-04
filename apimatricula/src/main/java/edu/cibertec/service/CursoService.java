package edu.cibertec.service;

import java.util.List;

import edu.cibertec.entity.CursoEntity;

/**
 * 🎓 Servicio de negocio para la gestión de cursos.
 * Define las operaciones de alto nivel utilizadas por los controladores.
 */
public interface CursoService {

    /**
     * Lista todos los cursos registrados.
     */
    List<CursoEntity> listarCursos();

    /**
     * Obtiene un curso según su ID.
     */
    CursoEntity obtenerCurso(Integer idCurso);

    /**
     * Registra un nuevo curso en la base de datos.
     */
    CursoEntity registrarCurso(CursoEntity curso);

    /**
     * Actualiza la información de un curso existente.
     */
    CursoEntity actualizarCurso(CursoEntity curso);

    /**
     * Elimina un curso por su ID.
     */
    void eliminarCurso(Integer idCurso);
}