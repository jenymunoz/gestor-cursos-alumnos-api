package com.portfolio.gestorcursosyalumnos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Clase encargada de mostrar los datos de un curso.")
public class RespuestaCursoDto {
    @Schema(
            description = "Id generado automáticamente"
    )
    private Long id;
    private String nombre;
    private String descripcion;
    private int duracion;
}
