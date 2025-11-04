package edu.cibertec.repository.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import edu.cibertec.entity.CursoEntity;
import edu.cibertec.repository.CursoRepository;

/**
 * 🗄️ Implementación JDBC pura del repositorio de cursos.
 *
 * Utiliza una conexión directa definida en `application.properties`
 * con los parámetros de conexión a la base de datos.
 */
@Repository
public class CursoRepositoryImpl implements CursoRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // =====================================================
    // 🔹 Listar cursos
    // =====================================================
    @Override
    public List<CursoEntity> listarCursos() {
        List<CursoEntity> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso ORDER BY idcurso";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CursoEntity c = new CursoEntity();
                c.setIdCurso(rs.getInt("idcurso"));
                c.setNombreCurso(rs.getString("nomcurso"));
                c.setFechaInicio(rs.getDate("fechainicio"));
                c.setAlumnosMinimo(rs.getInt("alumnosmin"));
                c.setAlumnosActual(rs.getInt("alumnosact"));
                c.setEstado(rs.getInt("estado"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    // =====================================================
    // 🔹 Obtener curso por ID
    // =====================================================
    @Override
    public CursoEntity obtenerCurso(Integer idCurso) {
        String sql = "SELECT * FROM curso WHERE idcurso = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurso);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CursoEntity(
                            rs.getInt("idcurso"),
                            rs.getString("nomcurso"),
                            rs.getDate("fechainicio"),
                            rs.getInt("alumnosmin"),
                            rs.getInt("alumnosact"),
                            rs.getInt("estado")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // =====================================================
    // 🔹 Registrar nuevo curso
    // =====================================================
    @Override
    public CursoEntity registrarCurso(CursoEntity curso) {
        String sql = "INSERT INTO curso (nomcurso, fechainicio, alumnosmin, alumnosact, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, curso.getNombreCurso());
            ps.setDate(2, curso.getFechaInicio());
            ps.setInt(3, curso.getAlumnosMinimo());
            ps.setInt(4, curso.getAlumnosActual());
            ps.setInt(5, curso.getEstado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    curso.setIdCurso(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    // =====================================================
    // 🔹 Actualizar curso existente
    // =====================================================
    @Override
    public CursoEntity actualizarCurso(CursoEntity curso) {
        String sql = """
            UPDATE curso
            SET nomcurso=?, fechainicio=?, alumnosmin=?, alumnosact=?, estado=?
            WHERE idcurso=?
        """;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, curso.getNombreCurso());
            ps.setDate(2, curso.getFechaInicio());
            ps.setInt(3, curso.getAlumnosMinimo());
            ps.setInt(4, curso.getAlumnosActual());
            ps.setInt(5, curso.getEstado());
            ps.setInt(6, curso.getIdCurso());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    // =====================================================
    // 🔹 Eliminar curso
    // =====================================================
    @Override
    public void eliminarCurso(Integer idCurso) {
        String sql = "DELETE FROM curso WHERE idcurso = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCurso);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}