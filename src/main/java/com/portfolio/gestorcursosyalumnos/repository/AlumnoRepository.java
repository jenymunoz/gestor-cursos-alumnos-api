package com.portfolio.gestorcursosyalumnos.repository;

import com.portfolio.gestorcursosyalumnos.model.Alumno;
import com.portfolio.gestorcursosyalumnos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query("Select a from Alumno a WHERE a.email= :email")
    Optional<Alumno> buscarPorEmail(String email);

    @Query("Select a.curso from Alumno a WHERE a.email=:email")
    Optional<Curso> buscarCurso(String email);

}
