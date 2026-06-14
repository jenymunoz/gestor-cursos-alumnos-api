package com.portfolio.gestorcursosyalumnos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CrearCursoDto {
    @NotBlank
    @Size(min = 5, max = 50)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 100)
    private String descripcion;

    @NotNull
    @Min(1)
    private int duracion;
}
