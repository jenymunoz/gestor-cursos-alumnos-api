package com.portfolio.gestorcursosyalumnos.service;

import com.portfolio.gestorcursosyalumnos.dto.AlumnoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.CrearAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.exception.AlumnoNoEncontradoException;
import com.portfolio.gestorcursosyalumnos.exception.AlumnoYaRegistradoException;
import com.portfolio.gestorcursosyalumnos.exception.CursoNoEncontradoException;
import com.portfolio.gestorcursosyalumnos.mapper.AlumnoMapper;
import com.portfolio.gestorcursosyalumnos.mapper.CursoMapper;
import com.portfolio.gestorcursosyalumnos.model.Alumno;
import com.portfolio.gestorcursosyalumnos.model.Curso;
import com.portfolio.gestorcursosyalumnos.repository.AlumnoRepository;
import com.portfolio.gestorcursosyalumnos.repository.CursoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoService {

    private final AlumnoRepository repository;
    private final CursoRepository cursoRepository;

    //CREATE
    public void registrarAlumno(CrearAlumnoDto dto){
        String emailDto = dto.getEmail().toLowerCase().trim();
        if (repository.findByEmail(dto.getEmail()).isPresent()){
            throw new AlumnoYaRegistradoException("El alumno ya fue registrado.");
        }

        validarFechaNacimiento(dto.getFechaNacimiento());

        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(()-> new CursoNoEncontradoException("El curso no fue encontrado"));

        Alumno alumno = AlumnoMapper.dtoToAlumno(dto, curso);
        alumno.setEmail(emailDto);
        repository.save(alumno);
    }

    public void validarFechaNacimiento(LocalDate fecha){
        LocalDate hoy = LocalDate.now();
        int diferencia = Period.between(fecha, hoy).getYears();

        if (diferencia<5 || diferencia>75) {
            throw new IllegalArgumentException(
                    "La edad de los alumnos debe estar entre 5 y 75 años.");
        }
    }

    //READ
    public List<RespuestaAlumnoDto> leerAlumnos(){
        return repository.findAll(Sort.by("curso").descending()).stream()
                .map(AlumnoMapper::alumnoToDto)
                .toList();
    }

    public List<RespuestaAlumnoDto> leerAlumnosPorPagina(int pagina, int dimension){
        Sort sort = Sort.by(Sort.Direction.DESC, "nombre");
        Pageable pageable = PageRequest.of(pagina, dimension, sort);

        return repository.findAll(pageable).stream()
                .map(AlumnoMapper::alumnoToDto)
                .toList();
    }

    public RespuestaCursoDto alumnoLeerCurso(String email){
        Curso curso = repository.findCurso(email)
                .orElseThrow(() -> new CursoNoEncontradoException("No se encontró el curso."));
        return CursoMapper.cursoToDto(curso);
    }

    public Alumno findAlumnoByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new AlumnoNoEncontradoException(
                        "El alumno no fue encontrado."));
    }

    public Alumno findAlumnoById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new AlumnoNoEncontradoException(
                        "El alumno no fue encontrado."));
    }

    public RespuestaAlumnoDto findAlumnoByEmailDto(String email){
        return AlumnoMapper.alumnoToDto(findAlumnoByEmail(email));
    }

    public RespuestaAlumnoDto findAlumnoByIdDto(Long id){
        return AlumnoMapper.alumnoToDto(findAlumnoById(id));
    }

    //UPDATE
    @Transactional
    public void actualizarAlumnoPorEmail(String email, AlumnoActualizacionDto dto){
        Alumno alumno = findAlumnoByEmail(email);

        if ((!dto.getEmail().isEmpty())&&repository.findByEmail(dto.getEmail()).isPresent()){
            throw new AlumnoYaRegistradoException(
                    "Ya existe un alumno con este email.");
        }

        if (dto.getFechaNacimiento()!=null){
            validarFechaNacimiento(dto.getFechaNacimiento());
        }
        Alumno actualizacion =
        AlumnoMapper.updateToAlumno(dto,alumno);
        repository.save(actualizacion);
    }

    @Transactional
    public void actualizarAlumnoPorId(Long id, AlumnoActualizacionDto dto){
        Alumno alumno = findAlumnoById(id);

        if (dto.getEmail()!=null){
            if (repository.findByEmail(dto.getEmail()).isPresent()){
                throw new AlumnoYaRegistradoException(
                        "Ya existe un alumno con este email.");
            }
        }

        if (dto.getFechaNacimiento()!=null){
            validarFechaNacimiento(dto.getFechaNacimiento());
        }
        Alumno actualizacion =
                AlumnoMapper.updateToAlumno(dto,alumno);
        repository.save(actualizacion);
    }

    //DELETE
    public void eliminarPorEmail(String email){
        Alumno alumno = findAlumnoByEmail(email);
        repository.delete(alumno);
    }

    public void eliminarPorId(Long id){
        Alumno alumno = findAlumnoById(id);
        repository.delete(alumno);
    }

}
