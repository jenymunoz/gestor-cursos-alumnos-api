package com.portfolio.gestorcursosyalumnos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Clase encargada de recoger los datos para registrar un nuevo alumno. " +
        "El Id será generado automáticamente.")
public class CrearCursoDto {
    @NotBlank
    @Size(min = 5, max = 50)
    @Schema(
            description = "Nombre del curso",
            example = "Diseño de API REST"
    )
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 100)
    @Schema(
            description = "Breve descripción del propósito del curso. No más de 100 caracteres",
            example = "Estudio y aplicación de los patrones de diseño que nos ayudarán a crear API REST " +
                    "seguras y escalables"
    )
    private String descripcion;

    @NotNull
    @Min(5) @Max(3000)
    @Schema(
            description = "Total de horas que dura el curso. Un curso debe durar almenos 5 horas",
            example = "5"
    )
    private int duracion;
}
