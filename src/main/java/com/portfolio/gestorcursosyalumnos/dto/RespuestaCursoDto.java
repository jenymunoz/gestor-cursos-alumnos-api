package com.portfolio.gestorcursosyalumnos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RespuestaCursoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private int duracion;
}
