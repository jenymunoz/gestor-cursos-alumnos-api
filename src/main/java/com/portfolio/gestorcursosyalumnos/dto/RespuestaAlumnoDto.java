package com.portfolio.gestorcursosyalumnos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor

@Schema(description = "Clase encargada de mostrar los datos de un alumno para solo lectura. " +
        "No incluye el curso del alumno.")
public class RespuestaAlumnoDto {
    @Schema(
            description = "Identificador generado automáticamente"
    )
    private Long id;
    private String nombre;
    private String email;
    private LocalDate fechaNacimiento;
}
