package com.portfolio.gestorcursosyalumnos.dto;

import com.portfolio.gestorcursosyalumnos.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RespuestaAlumnoDto {
    private Long id;
    private String nombre;
    private String email;
    private LocalDate fechaNacimiento;
}
