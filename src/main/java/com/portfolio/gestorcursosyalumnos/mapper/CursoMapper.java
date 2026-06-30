package com.portfolio.gestorcursosyalumnos.mapper;

import com.portfolio.gestorcursosyalumnos.dto.CrearCursoDto;
import com.portfolio.gestorcursosyalumnos.dto.CursoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.model.Curso;

public class CursoMapper {

    public static Curso dtoToCurso(CrearCursoDto dto){
        return Curso.builder()
                .nombre(dto.getNombre().toLowerCase())
                .descripcion(dto.getDescripcion())
                .duracion(dto.getDuracion())
                .build();
    }

    public static void updateToCurso(CursoActualizacionDto dto, Curso curso){

        if (dto.getDescripcion()!=null&&!dto.getDescripcion().isBlank()){
            curso.setDescripcion(dto.getDescripcion());
        }

    }

    public static RespuestaCursoDto cursoToDto(Curso curso){
        return new RespuestaCursoDto(curso.getId(),curso.getNombre()
                ,curso.getDescripcion(),curso.getDuracion());
    }



}
