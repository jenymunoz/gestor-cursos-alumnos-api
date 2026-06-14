package com.portfolio.gestorcursosyalumnos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class AlumnoActualizacionDto {
    @Size(min = 2, max = 50)
    private String nombre;

    @Email(message = "El correo no tiene un formato correcto")
    private String email;

    private LocalDate fechaNacimiento;

    private Long idCurso;
}
