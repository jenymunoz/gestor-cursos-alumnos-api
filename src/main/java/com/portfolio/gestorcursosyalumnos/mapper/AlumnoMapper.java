package com.portfolio.gestorcursosyalumnos.mapper;

import com.portfolio.gestorcursosyalumnos.dto.AlumnoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.CrearAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaAlumnoDto;
import com.portfolio.gestorcursosyalumnos.model.Alumno;
import com.portfolio.gestorcursosyalumnos.model.Curso;

public class AlumnoMapper {

    public static Alumno dtoToAlumno(CrearAlumnoDto dto, Curso curso){
        return Alumno.builder()
                .nombre(dto.getNombre())
                .fechaNacimiento(dto.getFechaNacimiento())
                .curso(curso)
                .build();
    }

    public static Alumno updateToAlumno (AlumnoActualizacionDto dto, Alumno alumno){
        if (dto.getNombre()!=null&&!dto.getNombre().isEmpty()){
            alumno.setNombre(dto.getNombre());
        }

        if (dto.getFechaNacimiento()!=null){
            alumno.setFechaNacimiento(dto.getFechaNacimiento());
        }
        return alumno;
    }

    public static RespuestaAlumnoDto alumnoToDto(Alumno alumno){
        return new RespuestaAlumnoDto(
                alumno.getId(), alumno.getNombre(), alumno.getEmail(),
                alumno.getFechaNacimiento());
    }

}
