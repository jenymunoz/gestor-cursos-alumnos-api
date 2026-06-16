package com.portfolio.gestorcursosyalumnos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
        info = @Info(
                title = "Gestor Cursos y Alumnos",
                version = "1.0.0",
                description = "API REST que ayuda a la gestión alumnos matriculados en distintos cursos. " +
                        "Un alumno no puede ser registrado si no existe un curso. " +
                        "Un curso no puede ser eliminado si tiene alumnos inscritos en él. " +
                        "Los datos se persisten usando MySQL através de una relación bi-direccional entre las entidades.",
                contact = @Contact(
                        name = "Jeny Muñoz",
                        email = "mellomunozmejia@gmail.com"
                )
        )
)
@SpringBootApplication
public class GestorcursosyalumnosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestorcursosyalumnosApplication.class, args);
    }

}
