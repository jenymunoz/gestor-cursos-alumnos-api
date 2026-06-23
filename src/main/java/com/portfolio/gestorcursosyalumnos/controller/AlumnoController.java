package com.portfolio.gestorcursosyalumnos.controller;

import com.portfolio.gestorcursosyalumnos.dto.AlumnoActualizacionDto;
import com.portfolio.gestorcursosyalumnos.dto.CrearAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaAlumnoDto;
import com.portfolio.gestorcursosyalumnos.dto.RespuestaCursoDto;
import com.portfolio.gestorcursosyalumnos.service.AlumnoService;
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
@RequestMapping("/api/alumnos")
@Tag(name = "Contolador para los alumnos.",
        description = "CRUD sobre la entidad alumnos.")

@AllArgsConstructor
public class AlumnoController {
    private final AlumnoService service;

    //CREATE
    @Operation(summary = "Registrar un nuevo alumno",
    description = "Debes tener en cuenta: no se admiten alumnos menores de 5 años, " +
            "el nombre no debe tener menos de 5 o más de 50 caracteres, " +
            "los alumnos deben tener un email único y es muy importante saber que " +
            "debe existir un curso para que el alumno pueda ser matriculado.",
    tags={"Registro"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nuevo alumno resgistrado con exito."),
            @ApiResponse(responseCode = "409", description = "El alumno ya fue registrado anteriormente."),
            @ApiResponse(responseCode = "400", description = "Valor de atributo incorrecto.")
    })
    @PostMapping("/alumno")
    public ResponseEntity<Void> registrar(@Valid @RequestBody CrearAlumnoDto dto){
        service.registrarAlumno(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //READ
    @Operation(summary = "Leer todos los alumnos",
            description = "Devuelve todos los registros dentro de la tabla Alumnos.",
            tags={"Lectura"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada")
    })
    @GetMapping("/alumnos")
    public ResponseEntity<List<RespuestaAlumnoDto>> todos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.leerAlumnos());
    }

    @Operation(summary = "Leer alumnos por página",
            description = "Devuelve los registros dentro de la tabla Alumnos pero usando páginación",
            tags={"Lectura"})
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
    @GetMapping("/pagina/alumnos")
    public ResponseEntity<List<RespuestaAlumnoDto>> leerAlumnosPorPagina(@RequestParam int pagina, @RequestParam int dimension){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.leerAlumnosPorPagina(pagina, dimension));
    }

    @Operation(summary = "Encontrar alumno por Id",
            description = "Ingresa el Id del alumno que quieres consultar",
            tags={"Lectura"})
    @Parameter(name = "id", description = "Los Id comienzan desde 1", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    @GetMapping("/alumno/id/{id}")
    public ResponseEntity<RespuestaAlumnoDto> encontrarAlumnoPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAlumnoByIdDto(id));
    }

    @Operation(summary = "Encontrar alumno por Email",
            description = "Ingresa el Email del alumno que quieres consultar",
            tags={"Lectura"})
    @Parameter(name = "email", description = "Debes conocer el email del alumno", required = true, example = "ejemplo1@gmail.com")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    @GetMapping("/alumno/email/{email}")
    public ResponseEntity<RespuestaAlumnoDto> encontrarAlumnoPorEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAlumnoByEmailDto(email));
    }

    @Operation(summary = "Encontrar el curso de un alumno",
            description = "Ingresa el email del alumno y obtendrás el curso en el que está matriculado.",
            tags={"Lectura"})
    @Parameter(name = "email", description = "Debes conocer el email del alumno", required = true, example = "ejemplo1@gmail.com")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura realizada"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    @GetMapping("/alumno/{email}/curso")
    public ResponseEntity<RespuestaCursoDto> alumnoLeerCurso(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.alumnoLeerCurso(email));
    }

    //UPDATE
    @Operation(summary = "Actualizar un alumno por Id",
            description = "Ingresa el Id del alumno y el o los atributos que quieres actualizar. " +
                    "Atributos que puedes actualizar: nombre, email, curso y fecha de nacimiento. " +
                    "No se permiten alumnos menores de 5 años, "+
                    "el nombre no debe tener menos de 5 o más de 50 caracteres.",
            tags={"Actualización"})
    @Parameter(name = "id", description = "Los Id comienzan desde 1", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización realizada"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado"),
            @ApiResponse(responseCode = "400", description = "Argumento invalido"),
            @ApiResponse(responseCode = "409", description = "Conflicto. No se admiten duplicados")
    })
    @PatchMapping("/alumno/id/{id}")
    public ResponseEntity<Void> actualizarAlumnoPorId(@PathVariable Long id, @RequestBody @Valid AlumnoActualizacionDto dto){
        service.actualizarAlumnoPorId(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Actualizar un alumno por email",
            description = "Ingresa el email del alumno y el o los atributos que quieres actualizar. " +
                    "Atributos que puedes actualizar: nombre, email, curso y fecha de nacimiento. " +
                    "No se permiten alumnos menores de 5 años, "+
                    "el nombre no debe tener menos de 5 o más de 50 caracteres, ",
            tags={"Actualización"})
    @Parameter(name = "email", description = "Debes conocer el email del alumno", required = true, example = "ejemplo2@gmail.com")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización realizada"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado"),
            @ApiResponse(responseCode = "400", description = "Argumento invalido"),
            @ApiResponse(responseCode = "409", description = "Conflicto. No se admiten duplicados")
    })
    @PatchMapping("/alumno/email/{email}")
    public ResponseEntity<Void> actualizarAlumnoPorEmail(@PathVariable String email, @RequestBody @Valid AlumnoActualizacionDto dto){
        service.actualizarAlumnoPorEmail(email, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    //DELETE
    @Operation(summary = "Eliminar un alumno por Id",
            description = "Ingresa el Id del alumno que quieres eliminar. " +
                    "¡Atención! Una vez realizada esta acción no puede deshacerse",
            tags={"Eliminación"})
    @Parameter(name = "id",description = "Los id comienzan desde 1", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alumno eliminado"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")})
    @DeleteMapping("/alumno/id/{id}")
    public ResponseEntity<Void> eliminarAlumnoPorId(@PathVariable Long id){
        service.eliminarPorId(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Eliminar un alumno por email",
            description = "Ingresa el email del alumno que quieres eliminar. " +
                    "¡Atención! Una vez realizada esta acción no puede deshacerse",
            tags={"Eliminación"})
    @Parameter(name = "Email",description = "Debes conocer el email del alumno", required = true, example = "ejemplo2@gmail.com")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alumno eliminado"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")})
    @DeleteMapping("/alumno/email/{email}")
    public ResponseEntity<Void> eliminarAlumnoPorEmail(@PathVariable String email){
        service.eliminarPorEmail(email);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }







}
