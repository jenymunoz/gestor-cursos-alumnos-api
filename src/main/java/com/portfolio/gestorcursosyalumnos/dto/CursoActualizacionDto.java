package com.portfolio.gestorcursosyalumnos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@Schema(description = "Clase encargada de recoger los datos para la actualización de un curso.")
public class CursoActualizacionDto {

    @Size(min = 5, max = 50)
    @Schema(
            description = "Nuevo nombre del curso. Debe ser único con no más de 50 caracteres",
            example = "Diseño de API REST en JAVA"
    )
    private String nombre;

    @Size(min = 5, max = 100)
    @Schema(
            description = "Nueva descripción del curso. No debe tener menos de 5 o más de 100 caracteres",
            example = "Aplicaremos diferentes patrones de diseño a la creación de API REST con JAVA"
    )
    private String descripcion;

    @Min(1)
    @Schema(
            description = "Nueva duración en horas del curso. Un curso debe durar almenos una hora",
            example = "1"
    )
    private Integer duracion;
}
