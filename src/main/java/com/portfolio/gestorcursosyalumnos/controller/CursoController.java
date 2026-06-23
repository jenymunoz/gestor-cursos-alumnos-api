package com.portfolio.gestorcursosyalumnos.controller;

import com.portfolio.gestorcursosyalumnos.dto.CrearCursoDto;
import com.portfolio.gestorcursosyalumnos.dto.CursoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@Tag(name = "Contolador para los cursos.",
        description = "CRUD sobre la entidad Cursos.")
@AllArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    //CREATE
    @Operation(summary = "Registrar un nuevo curso",
            description = "Antes de registrar un nuevo curso debes saber: " +
                    "los nombres de los cursos deben ser únicos y " +
                    "no tener menos de 5 o más de 100 caracteres, " +
                    "la duración de los cursos debe ser escrita en horas, un curso debe durar almenos una hora",
    tags = {"Registro"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nuevo curso registrado con exito."),
            @ApiResponse(responseCode = "409", description = "El curso ya fue registrado anteriormente."),
            @ApiResponse(responseCode = "400", description = "Valor de atributo incorrecto")
    })
    @PostMapping("/curso")
    public ResponseEntity<Void> registrar(@Valid @RequestBody CrearCursoDto dto){
        cursoService.registrarCurso(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //READ
    @Operation(summary = "Leer todos los cursos",
            description = "Devuelve todos los registros dentro de la tabla cursos.",
            tags = {"Lectura"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada")
    })
    @GetMapping("/cursos")
    public ResponseEntity<List<RespuestaCursoDto>> todos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.todos());
    }

    @Operation(summary = "Leer cursos por página",
            description = "Devuelve todos los registros dentro de la tabla cursos según la página que consultes",
            tags = {"Lectura"})
    @Parameter(
            name = "pagina",
            description = "Las páginas comienzan desde 0",
            required = true,
            example = "0"
    )
    @Parameter(
            name = "dimension",
            description = "Se refiere al número de registros que deas recoger",
            required = true,
            example = "2"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada")
    })
    @GetMapping("/pagina/dto/cursos")
    public ResponseEntity<List<RespuestaCursoDto>> todosPorPagina(@RequestParam int pagina, @RequestParam int dimension){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.todosPorPagina(pagina, dimension));
    }

    @Operation(summary = "Encontrar curso por Id",
            description = "Ingresa el Id del curso que quieres consular",
            tags = {"Lectura"})
    @Parameter(name = "id", description = "Los id comienzan desde 1", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/curso/id/{id}")
    public ResponseEntity<RespuestaCursoDto> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.encontrarPorIdDto(id));
    }

    @Operation(summary = "Encontrar curso por nombre",
            description = "Ingresa el nombre del curso que quieres consular",
            tags = {"Lectura"})
    @Parameter(name = "nombre", description = "Ingresa el nombre del curso", required = true, example = "Diseño")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/curso/nombre/{nombre}")
    public ResponseEntity<RespuestaCursoDto> encontrarPorNombre(@PathVariable String nombre){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.encontrarPorNombreDto(nombre));
    }

    @Operation(summary = "Listar alumnos de un curso por Id",
            description = "Ingresa el Id del curso", tags = "Lectura")
    @Parameter(name = "id", description = "Los id comienzan desde 1", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/curso/id/{id}/alumnos")
    public ResponseEntity<List<RespuestaAlumnoDto>> alumnosDeUnCursoPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.alumnosDeUnCursoPorId(id));
    }

    @Operation(summary = "Listar alumnos de un curso por nombre",
            description = "Ingresa el nombre del curso", tags = "Lectura")
    @Parameter(name = "nombre", description = "Ingresa el nombre del curso", required = true, example = "Diseño")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/curso/nombre/{nombre}/alumnos")
    public ResponseEntity<List<RespuestaAlumnoDto>> alumnosDeUnCursoPorNombre(@PathVariable String nombre){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoService.alumnosDeUnCursoPorNombre(nombre));
    }

    //UPDATE
    @Operation(summary = "Actualizar un curso por Id",
            description = "Ingresa el Id del curso y el o los atributos que quieres actualizar. " +
                    "Atributos que puedes actualizar: nombre, duración y descripción. " +
                    "Los nombres de los cursos deben ser únicos y descriptivos y no tener menos de 2 o más de 50 caracteres, " +
                    "un curso debe durar al menos una hora, la descripción debe ser breve y no debe tener menos de 5 o más de 100 caracteres.",
            tags={"Actualización"})
    @Parameter(name = "id", description = "Los Id comienzan desde 1", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización realizada"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado"),
            @ApiResponse(responseCode = "400", description = "Argumento invalido"),
            @ApiResponse(responseCode = "409", description = "Conflicto. No se admiten duplicados")
    })
    @PatchMapping("/curso/id/{id}")
    public ResponseEntity<Void> actualizarCursoPorId(@PathVariable Long id, @RequestBody @Valid CursoActualizacionDto dto){
        cursoService.actualizarCursoPorId(id, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Actualizar un curso por nombre",
            description = "Ingresa el nombre del curso y el o los atributos que quieres actualizar. " +
                    "Atributos que puedes actualizar: nombre, duración y descripción. " +
                    "Los nombres de los cursos deben ser únicos y descriptivos y no tener menos de 2 o más de 50 caracteres, " +
                    "un curso debe durar al menos una hora, la descripción debe ser breve y no debe tener menos de 5 o más de 100 caracteres.",
            tags={"Actualización"})
    @Parameter(name = "nombre", description = "Ingresa el nombre del curso", required = true, example = "Diseño")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización realizada"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado"),
            @ApiResponse(responseCode = "400", description = "Argumento invalido"),
            @ApiResponse(responseCode = "409", description = "Conflicto. No se admiten duplicados")
    })
    @PatchMapping("/curso/nombre/{nombre}")
    public ResponseEntity<Void> actualizarCursoPorNombre(@PathVariable String nombre,@RequestBody @Valid CursoActualizacionDto dto){
        cursoService.actualizarCursoPorNombre(nombre, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //DELETE
    @Operation(summary = "Eliminar un curso por Id",
                description = "Ingresa el Id del curso que quieres eliminar. " +
                        "¡Atención! Una vez realizada esta acción no puede deshacerse",
            tags = {"Eliminación"})
    @Parameter(name = "id", description = "Los id comienzan desde 1", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")})
    @DeleteMapping("/curso/id/{id}")
    public ResponseEntity<Void> eliminarCursoPorId(@PathVariable Long id){
       cursoService.eliminarCursoPorId(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Eliminar un curso por nombre",
            description = "Ingresa el nombre del curso que quieres eliminar. " +
                    "¡Atención! Una vez realizada esta acción no puede deshacerse",
            tags = {"Eliminación"})
    @Parameter(name = "nombre", description = "Ingresa el nombre del curso", required = true, example = "Diseño")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")})
    @DeleteMapping("/curso/nombre/{nombre}")
    public ResponseEntity<Void> eliminarCursoPorNombre(@PathVariable String nombre){
        cursoService.eliminarCursoPorNombre(nombre);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
