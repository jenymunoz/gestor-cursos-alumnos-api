package com.portfolio.gestorcursosyalumnos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@Schema(description = "Clase encargada de recoger los datos para actualizar un alumno.")
public class AlumnoActualizacionDto {
    @Size(min = 2, max = 50)
    @Schema(
            description = "Nuevo nombre del alumno. No menos de 2 o más de 50 caracteres",
            example = "Jeny M Muñoz"
    )
    private String nombre;

    @Email(message = "El correo no tiene un formato correcto")
    @Schema(
            description = "Nuevo correo del alumno. Debe ser único.",
            example = "jenyejemplo@gmail.com"
    )
    private String email;

    @Schema(
            description = "Nueva fecha de nacimiento. Formato de fecha: año-mes-día",
            example = "2020-10-16"
    )
    private LocalDate fechaNacimiento;

    @Schema(
            description = "Nuevo curso del alumno.",
            example = "1"
    )
    private Long idCurso;
}
