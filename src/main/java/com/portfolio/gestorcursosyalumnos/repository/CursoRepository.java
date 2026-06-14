package com.portfolio.gestorcursosyalumnos.repository;

import com.portfolio.gestorcursosyalumnos.model.Alumno;
import com.portfolio.gestorcursosyalumnos.model.Curso;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE c.nombre=:name")
    Optional<Curso> findByName(String name);

    @Query("SELECT c.alumnos FROM Curso c WHERE c.id=:id")
    List<Alumno> findAlumnosByIdCurso(Long id);

    @Query("SELECT c.alumnos FROM Curso c WHERE c.nombre=:name")
    List<Alumno> findAlumnosByNameCurso(String name);


}
