package com.portfolio.gestorcursosyalumnos.service;

import com.portfolio.gestorcursosyalumnos.dto.CrearCursoDto;
import com.portfolio.gestorcursosyalumnos.dto.CursoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.exception.CursoNoEncontradoException;
import com.portfolio.gestorcursosyalumnos.exception.CursoOcupadoException;
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

    public void registrarCurso(CrearCursoDto dto){
        if (repository.buscarPorNombre(dto.getNombre().toLowerCase()).isPresent()){
            throw new CursoYaRegistradoException(
                    "Ya existe un curso registrado con el nombre: "+dto.getNombre());
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
        Curso curso = encontrarPorId(id);

        if (dto.getNombre()!=null&&!dto.getNombre().isEmpty()){
            String dtoNombre = dto.getNombre().toLowerCase();
            if (!curso.getNombre().equalsIgnoreCase(dtoNombre)){
                if (repository.buscarPorNombre(dtoNombre).isPresent()){
                    throw new CursoYaRegistradoException(
                            "Ya existe un curso registrado con el nombre: "+dto.getNombre());
                }
            }
            curso.setNombre(dtoNombre);
        }

        if (dto.getDuracion()!=null){
            validarDuracionDelCurso(dto.getDuracion());
            curso.setDuracion(dto.getDuracion());
        }

        CursoMapper.updateToCurso(dto, curso);
    }

    @Transactional
    public void actualizarCursoPorNombre(String nombre, CursoActualizacionDto dto){
        Curso curso = encontrarPorNombre(nombre.toLowerCase());

        if (dto.getNombre()!=null&&!dto.getNombre().isEmpty()){
            String dtoNombre = dto.getNombre().toLowerCase();
            if (!curso.getNombre().equalsIgnoreCase(dtoNombre)){
                if (repository.buscarPorNombre(dtoNombre).isPresent()){
                    throw new CursoYaRegistradoException(
                            "Ya existe un curso registrado con el nombre: "+dto.getNombre());
                }
            }
            curso.setNombre(dtoNombre);
        }

        if (dto.getDuracion()!=null){
            validarDuracionDelCurso(dto.getDuracion());
            curso.setDuracion(dto.getDuracion());
        }

        CursoMapper.updateToCurso(dto, curso);

    }

    public void validarDuracionDelCurso(Integer horas){
        if (horas<1){
            throw new IllegalArgumentException("Los cursos deben durar almenos una hora.");
        }
    }

    //DELETE
    public void eliminarCursoPorId(Long id){
        Curso curso = repository.cursoCompletoPorId(id)
                .orElseThrow(()-> new CursoNoEncontradoException("El curso con id: "+id+", no fue encontrado."));

        if (!curso.getAlumnos().isEmpty()){
            throw new CursoOcupadoException("El curso no puede ser eliminado porque hay alumnos matriculados en él.");
        }else {
            repository.deleteById(id);
        }

    }

    public void eliminarCursoPorNombre(String nombre){
        Curso curso = repository.cursoCompletoPorNombre(nombre)
                .orElseThrow(()-> new CursoNoEncontradoException("El curso con id: "+nombre+", no fue encontrado."));

        if (!curso.getAlumnos().isEmpty()){
            throw new CursoOcupadoException("El curso no puede ser eliminado porque hay alumnos matriculados en él.");
        }else {
            repository.deleteById(curso.getId());
        }
    }

}

