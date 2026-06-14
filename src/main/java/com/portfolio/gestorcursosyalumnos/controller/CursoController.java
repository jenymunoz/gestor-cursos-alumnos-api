package com.portfolio.gestorcursosyalumnos.controller;

import com.portfolio.gestorcursosyalumnos.dto.CrearCursoDto;
import com.portfolio.gestorcursosyalumnos.dto.CursoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.service.CursoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    //CREATE
    @PostMapping("/curso")
    public ResponseEntity<Void> registrar(@Valid @RequestBody CrearCursoDto dto){
        cursoService.registrarCurso(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //READ
    @GetMapping("/cursos")
    public ResponseEntity<List<RespuestaCursoDto>> todos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.todos());
    }

    @GetMapping("/pagina/dto/cursos")
    public ResponseEntity<List<RespuestaCursoDto>> todosPorPagina(@RequestParam int pagina, @RequestParam int dimension){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.todosPorPagina(pagina, dimension));
    }

    @GetMapping("/curso/id/{id}")
    public ResponseEntity<RespuestaCursoDto> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.encontrarPorIdDto(id));
    }

    @GetMapping("/curso/nombre/{nombre}")
    public ResponseEntity<RespuestaCursoDto> encontrarPorNombre(@PathVariable String nombre){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.encontrarPorNombreDto(nombre));
    }

    @GetMapping("/curso/id/{id}/alumnos")
    public ResponseEntity<List<RespuestaAlumnoDto>> alumnosDeUnCursoPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.alumnosDeUnCursoPorId(id));
    }

    @GetMapping("/curso/nombre/{nombre}/alumnos")
    public ResponseEntity<List<RespuestaAlumnoDto>> alumnosDeUnCursoPorNombre(@PathVariable String nombre){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.alumnosDeUnCursoPorNombre(nombre));
    }

    //UPDATE
    @PatchMapping("/curso/id/{id}")
    public ResponseEntity<Void> actualizarCursoPorId(@PathVariable Long id, @RequestBody @Valid CursoActualizacionDto dto){
        cursoService.actualizarCursoPorId(id, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/curso/nombre/{nombre}")
    public ResponseEntity<Void> actualizarCursoPorNombre(@PathVariable String nombre,@RequestBody @Valid CursoActualizacionDto dto){
        cursoService.actualizarCursoPorNombre(nombre, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //DELETE
    @DeleteMapping("/curso/id/{id}")
    public ResponseEntity<Void> eliminarCursoPorId(@PathVariable Long id){
       cursoService.eliminarCursoPorId(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/curso/nombre/{nombre}")
    public ResponseEntity<Void> eliminarCursoPorNombre(@PathVariable String nombre){
        cursoService.eliminarCursoPorNombre(nombre);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
