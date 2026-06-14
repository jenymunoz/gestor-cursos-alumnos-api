package com.portfolio.gestorcursosyalumnos.controller;

import com.portfolio.gestorcursosyalumnos.dto.AlumnoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.CrearAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.model.Curso;
import com.portfolio.gestorcursosyalumnos.service.AlumnoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")

@AllArgsConstructor
public class AlumnoController {
    private final AlumnoService service;

    //CREATE
    @PostMapping("/alumno")
    public ResponseEntity<Void> registrar(@Valid @RequestBody CrearAlumnoDto dto){
        service.registrarAlumno(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //READ
    @GetMapping("/alumnos")
    public ResponseEntity<List<RespuestaAlumnoDto>> todos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.leerAlumnos());
    }

    @GetMapping("/pagina/alumnos")
    public ResponseEntity<List<RespuestaAlumnoDto>> leerAlumnosPorPagina(@RequestParam int pagina, @RequestParam int dimension){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.leerAlumnosPorPagina(pagina, dimension));
    }

    @GetMapping("/alumno/id/{id}")
    public ResponseEntity<RespuestaAlumnoDto> encontrarAlumnoPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAlumnoByIdDto(id));
    }

    @GetMapping("/alumno/email/{email}")
    public ResponseEntity<RespuestaAlumnoDto> encontrarAlumnoPorEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAlumnoByEmailDto(email));
    }

    @GetMapping("/alumno/{email}/curso")
    public ResponseEntity<RespuestaCursoDto> alumnoLeerCurso(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.alumnoLeerCurso(email));
    }

    //UPDATE
    @PatchMapping("/alumno/id/{id}")
    public ResponseEntity<Void> actualizarAlumnoPorId(@PathVariable Long id, @RequestBody @Valid AlumnoActualizacionDto dto){
        service.actualizarAlumnoPorId(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/alumno/email/{email}")
    public ResponseEntity<Void> actualizarAlumnoPorEmail(@PathVariable String email, @RequestBody @Valid AlumnoActualizacionDto dto){
        service.actualizarAlumnoPorEmail(email, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    //DELETE
    @DeleteMapping("/alumno/id/{id}")
    public ResponseEntity<Void> eliminarAlumnoPorId(@PathVariable Long id){
        service.eliminarPorId(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/alumno/email/{email}")
    public ResponseEntity<Void> eliminarAlumnoPorEmail(@PathVariable String email){
        service.eliminarPorEmail(email);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }







}
