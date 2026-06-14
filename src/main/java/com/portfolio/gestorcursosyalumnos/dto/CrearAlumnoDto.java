package com.portfolio.gestorcursosyalumnos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CrearAlumnoDto {

    @NotBlank
    @Size(min = 2, max = 50)
    private String nombre;

    @NotBlank
    @Email(message = "El correo no tiene un formato correcto")
    private String email;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    @Min(1)
    private Long idCurso;

}
