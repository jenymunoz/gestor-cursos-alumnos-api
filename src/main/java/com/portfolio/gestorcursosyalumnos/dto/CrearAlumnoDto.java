package com.portfolio.gestorcursosyalumnos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor

@Schema(description = "Datos para registrar un nuevo alumno. El Id será generado automáticamente")
public class CrearAlumnoDto {

    @NotBlank
    @Size(min = 2, max = 50)
    @Schema(
            description = "Nombre completo del alumno",
            example = "Jeny Melissa Muñoz Mejía"
    )
    private String nombre;

    @NotBlank
    @Email(message = "El correo no tiene un formato correcto")
    @Schema(
            description = "Correo electrónico del alumno. Debe ser único.",
            example = "jenymejia@gmail.com"
    )
    private String email;

    @NotNull
    @Schema(
            description = "Fecha de nacimiento. No menores de 5 años",
            example = "2001-04-15"
    )
    private LocalDate fechaNacimiento;

    @NotNull
    @Min(1)
    @Schema(
            description = "Identificador del curso al que pertenece el alumno.",
            example = "1"
    )
    private Long idCurso;

}
