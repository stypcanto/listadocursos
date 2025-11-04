package edu.cibertec.entity;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name="Curso", description = "Entidad que representa un curso")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcurso")
    private Integer idCurso;
    @Column(name = "nomcurso")
    private String nombreCurso;
    @Column(name = "fechainicio")
    private Date fechaInicio;
    @Column(name = "alumnosmin")
    private Integer alumnosMinimo;
    @Column(name = "alumnosact")
    private Integer alumnosActual;
    @Column(name = "estado")
    private Integer estado;
}
