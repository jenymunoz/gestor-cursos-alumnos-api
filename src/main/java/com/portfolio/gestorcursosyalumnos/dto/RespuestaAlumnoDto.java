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
            description = "Identificador generado automáticamente",
            example = "1"
    )
    private Long id;

    @Schema(
            example = "Hector Ramirez"
    )
    private String nombre;

    @Schema(
            example = "hectorramirez88@gmail.com"
            )
    private String email;
    @Schema(
            example = "1988-01-06"
    )
    private LocalDate fechaNacimiento;
}
