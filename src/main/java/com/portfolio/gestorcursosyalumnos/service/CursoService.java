package com.portfolio.gestorcursosyalumnos.service;

import com.portfolio.gestorcursosyalumnos.dto.CrearCursoDto;
import com.portfolio.gestorcursosyalumnos.dto.CursoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.exception.CursoNoEncontradoException;
import com.portfolio.gestorcursosyalumnos.exception.CursoYaRegistradoException;
import com.portfolio.gestorcursosyalumnos.mapper.AlumnoMapper;
import com.portfolio.gestorcursosyalumnos.mapper.CursoMapper;
import com.portfolio.gestorcursosyalumnos.model.Curso;
import com.portfolio.gestorcursosyalumnos.repository.CursoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CursoService {
    private final CursoRepository repository;

    //CREATE
    public void registrarCurso(CrearCursoDto dto){
        if (repository.buscarPorNombre(dto.getNombre()).isPresent()){
            throw new CursoYaRegistradoException(
                    "El curso ya fue registrado. Los cursos deben tener un nombre único.");
        }

        Curso curso = CursoMapper.dtoToCurso(dto);
        repository.save(curso);
    }

    //READ
    public List<RespuestaCursoDto> todos(){
        return repository.findAll().stream()
                .map(CursoMapper::cursoToDto)
                .toList();
    }

    public List<RespuestaCursoDto> todosPorPagina(int pagina, int dimension){
        Pageable pageable = PageRequest.of(pagina, dimension, Sort.by(Sort.Direction.DESC,"nombre"));

        return repository.findAll(pageable).stream()
                .map(CursoMapper::cursoToDto)
                .toList();
    }

    public List<RespuestaAlumnoDto> alumnosDeUnCursoPorId(Long id){
        return repository.buscarAlumnosPorIdCurso(id).stream()
                .map(AlumnoMapper::alumnoToDto).toList();
    }

    public List<RespuestaAlumnoDto> alumnosDeUnCursoPorNombre(String nombre){
        return repository.buscarAlumnosPorNombreCurso(nombre).stream()
                .map(AlumnoMapper::alumnoToDto).toList();
    }

    public Curso encontrarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new CursoNoEncontradoException("El curso no fue encontrado"));
    }

    public Curso encontrarPorNombre(String nombre){
        return repository.buscarPorNombre(nombre)
                .orElseThrow(()-> new CursoNoEncontradoException("El curso no fue encontrado"));
    }

    public RespuestaCursoDto encontrarPorIdDto(Long id){
        return CursoMapper.cursoToDto(encontrarPorId(id));
    }

    public RespuestaCursoDto encontrarPorNombreDto(String nombre){
        return CursoMapper.cursoToDto(encontrarPorNombre(nombre));
    }


    //UPDATE
    @Transactional
    public void actualizarCursoPorId(Long id, CursoActualizacionDto dto){
        Curso original = encontrarPorId(id);

        if (dto.getNombre()!=null&&!dto.getNombre().isEmpty()){
            if (repository.buscarPorNombre(dto.getNombre()).isPresent()){
            throw new CursoYaRegistradoException(
                    "El curso ya fue registrado. Los nombres deben ser únicos");
            }
        }

        Curso update = CursoMapper.updateToCurso(dto, original);
        repository.save(update);
    }

    @Transactional
    public void actualizarCursoPorNombre(String nombre, CursoActualizacionDto dto){
        Curso original = encontrarPorNombre(nombre);

        if (dto.getNombre()!=null&&!dto.getNombre().isEmpty()){
            if (repository.buscarPorNombre(dto.getNombre()).isPresent()){
                throw new CursoYaRegistradoException(
                        "El curso ya fue registrado. Los nombres deben ser únicos");
            }
        }

        Curso update = CursoMapper.updateToCurso(dto, original);
        repository.save(update);
    }


    //DELETE
    public void eliminarCursoPorId(Long id){
        Curso curso = encontrarPorId(id);
        repository.delete(curso);
    }

    public void eliminarCursoPorNombre(String nombre){
        Curso curso = encontrarPorNombre(nombre);
        repository.delete(curso);
    }

}
