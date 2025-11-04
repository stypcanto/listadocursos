package edu.cibertec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.cibertec.entity.CursoEntity;
import edu.cibertec.repository.CursoRepository;
import edu.cibertec.service.CursoService;
import lombok.RequiredArgsConstructor;

/**
 * 🎯 Implementación del servicio de cursos.
 * Intermedia entre el controlador y el repositorio REST.
 */
@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    public List<CursoEntity> listarCursos() {
        return cursoRepository.listarCursos();
    }

    @Override
    public CursoEntity obtenerCurso(Integer idCurso) {
        return cursoRepository.obtenerCurso(idCurso);
    }

    @Override
    public CursoEntity registrarCurso(CursoEntity curso) {
        return cursoRepository.registrarCurso(curso);
    }

    @Override
    public CursoEntity actualizarCurso(CursoEntity curso) {
        return cursoRepository.actualizarCurso(curso);
    }

    @Override
    public void eliminarCurso(Integer idCurso) {
        cursoRepository.eliminarCurso(idCurso);
    }
}