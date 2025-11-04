package edu.cibertec.repository.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import edu.cibertec.entity.CursoEntity;
import edu.cibertec.repository.CursoRepository;

/**
 * 💾 Implementación del repositorio que consume el backend REST de cursos.
 * Gestiona las operaciones CRUD mediante RestTemplate.
 */
@Repository
public class CursoRepositoryImpl implements CursoRepository {

    private final RestTemplate restTemplate;

    @Value("${uri.api.matricula}")
    private String URI_API;

    public CursoRepositoryImpl() {
        this.restTemplate = new RestTemplate();
    }

    // =====================================================
    // 🔹 Listar cursos
    // =====================================================
    @Override
    public List<CursoEntity> listarCursos() {
        ResponseEntity<CursoEntity[]> response =
                restTemplate.getForEntity(URI_API, CursoEntity[].class);
        CursoEntity[] cursos = response.getBody();
        return (cursos != null) ? Arrays.asList(cursos) : List.of();
    }

    // =====================================================
    // 🔹 Obtener curso por ID
    // =====================================================
    @Override
    public CursoEntity obtenerCurso(Integer idCurso) {
        try {
            return restTemplate.getForObject(URI_API + "/" + idCurso, CursoEntity.class);
        } catch (HttpClientErrorException.NotFound e) {
            System.out.println("⚠️ Curso no encontrado con ID " + idCurso);
            return null;
        }
    }

    // =====================================================
    // 🔹 Registrar o actualizar curso (inteligente)
    // =====================================================
    @Override
    public CursoEntity registrarCurso(CursoEntity curso) {
        try {
            if (curso.getIdCurso() != null && curso.getIdCurso() > 0) {
                // Si el curso tiene ID, se actualiza con PUT
                restTemplate.put(URI_API + "/" + curso.getIdCurso(), curso);
                System.out.println("🔄 Curso actualizado correctamente: " + curso.getNombreCurso());
                return curso;
            } else {
                // Si no tiene ID, se crea con POST
                CursoEntity nuevo = restTemplate.postForObject(URI_API, curso, CursoEntity.class);
                System.out.println("✅ Nuevo curso registrado: " + nuevo.getNombreCurso());
                return nuevo;
            }
        } catch (Exception ex) {
            System.err.println("❌ Error al registrar/actualizar curso: " + ex.getMessage());
            return null;
        }
    }

    // =====================================================
    // 🔹 Actualizar curso existente (uso directo)
    // =====================================================
    @Override
    public CursoEntity actualizarCurso(CursoEntity curso) {
        try {
            restTemplate.put(URI_API + "/" + curso.getIdCurso(), curso);
            System.out.println("📝 Curso actualizado correctamente con PUT");
            return curso;
        } catch (Exception ex) {
            System.err.println("❌ Error al actualizar curso: " + ex.getMessage());
            return null;
        }
    }

    // =====================================================
    // 🔹 Eliminar curso por ID
    // =====================================================
    @Override
    public void eliminarCurso(Integer idCurso) {
        try {
            restTemplate.exchange(URI_API + "/" + idCurso, HttpMethod.DELETE, null, Void.class);
            System.out.println("🗑️ Curso con ID " + idCurso + " eliminado correctamente.");
        } catch (HttpClientErrorException.NotFound e) {
            System.out.println("⚠️ No se encontró el curso con ID " + idCurso + " al intentar eliminar.");
        } catch (Exception ex) {
            System.err.println("❌ Error al eliminar curso con ID " + idCurso + ": " + ex.getMessage());
        }
    }
}