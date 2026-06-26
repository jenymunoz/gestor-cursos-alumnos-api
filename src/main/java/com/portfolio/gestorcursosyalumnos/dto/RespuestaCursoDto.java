package com.portfolio.gestorcursosyalumnos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Clase encargada de mostrar los datos de un curso.")
public class RespuestaCursoDto {
    @Schema(
            description = "Id generado automáticamente",
            example = "1"
    )
    private Long id;

    @Schema(
            example = "Introducción a la Programación"
    )
    private String nombre;

    @Schema(
            example = "Estudiaremos los fundamentos de la programación."
    )
    private String descripcion;
    @Schema(
            example = "120"
    )
    private int duracion;
}
