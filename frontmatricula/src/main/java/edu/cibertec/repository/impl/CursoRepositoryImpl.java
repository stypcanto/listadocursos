package edu.cibertec.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import edu.cibertec.entity.CursoEntity;
import edu.cibertec.repository.CursoRepository;

@Repository
public class CursoRepositoryImpl  implements CursoRepository {

    private RestTemplate restTemplate;

    @Value("${uri.api.matricula}")
    private String URI_API;

    public CursoRepositoryImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<CursoEntity> listarCursos() {
        return List.of(restTemplate.getForObject(URI_API, CursoEntity[].class));
    }

    @Override
    public CursoEntity obtenerCurso(Integer idCurso) {
        return restTemplate.getForObject(URI_API + "/" + idCurso, CursoEntity.class);
    }

    @Override
    public CursoEntity registrarCurso(CursoEntity curso) {
        return restTemplate.postForObject(URI_API, curso, CursoEntity.class);
    }

    @Override
    public CursoEntity actualizarCurso(CursoEntity curso) {
        restTemplate.put(URI_API + "/" + curso.getIdCurso(), curso);
        return curso;
    }

    @Override
    public CursoEntity eliminarCurso(Integer idCurso) {
        restTemplate.delete(URI_API + "/" + idCurso);
        return obtenerCurso(idCurso);
    }
    
}
