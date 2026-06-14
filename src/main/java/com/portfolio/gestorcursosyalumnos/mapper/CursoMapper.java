package com.portfolio.gestorcursosyalumnos.mapper;

import com.portfolio.gestorcursosyalumnos.dto.CrearCursoDto;
import com.portfolio.gestorcursosyalumnos.dto.CursoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.model.Curso;

public class CursoMapper {

    public static Curso dtoToCurso(CrearCursoDto dto){
        return Curso.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .duracion(dto.getDuracion())
                .build();
    }

    public static Curso updateToCurso(CursoActualizacionDto dto, Curso curso){
        if (dto.getNombre()!=null&&!dto.getNombre().isEmpty()){
            curso.setNombre(dto.getNombre());
        }

        if (dto.getDescripcion()!=null&&!dto.getDescripcion().isEmpty()){
            curso.setDescripcion(dto.getDescripcion());
        }

        if (dto.getDuracion()!=null&&dto.getDuracion()>=1){
            curso.setDuracion(dto.getDuracion());
        }
        return curso;
    }

    public static RespuestaCursoDto cursoToDto(Curso curso){
        return new RespuestaCursoDto(curso.getId(),curso.getNombre()
                ,curso.getDescripcion(),curso.getDuracion());
    }



}
