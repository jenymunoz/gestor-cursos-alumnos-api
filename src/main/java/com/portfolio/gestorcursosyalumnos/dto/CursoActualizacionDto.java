package com.portfolio.gestorcursosyalumnos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CursoActualizacionDto {

    @Size(min = 5, max = 50)
    private String nombre;

    @Size(min = 5, max = 100)
    private String descripcion;

    @Min(1)
    private Integer duracion;
}
