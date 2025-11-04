package edu.cibertec.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoEntity {
    private Integer idCurso;
    private String nombreCurso;
    private Date fechaInicio;
    private Integer alumnosMinimo;
    private Integer alumnosActual;
    private Integer estado;
}
