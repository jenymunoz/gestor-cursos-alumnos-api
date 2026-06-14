package com.portfolio.gestorcursosyalumnos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder().multipleErrors(errors).status(400).build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handlerIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder().message(ex.getMessage()).status(400).build());
    }

    @ExceptionHandler(AlumnoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlerAlumnoNoEncontradoException(AlumnoNoEncontradoException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder().message(ex.getMessage()).status(404).build());
    }

    @ExceptionHandler(AlumnoYaRegistradoException.class)
    public ResponseEntity<ErrorResponse> handlerAlumnoYaRegistradoException(AlumnoYaRegistradoException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder().message(ex.getMessage()).status(409).build());
    }

    @ExceptionHandler(CursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlerCursoNoEncontradoException(CursoNoEncontradoException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder().message(ex.getMessage()).status(404).build());
    }

    @ExceptionHandler(CursoYaRegistradoException.class)
    public ResponseEntity<ErrorResponse> handlerCursoYaRegistradoException(CursoYaRegistradoException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder().message(ex.getMessage()).status(409).build());
    }

}