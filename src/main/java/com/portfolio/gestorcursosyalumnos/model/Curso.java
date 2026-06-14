package com.portfolio.gestorcursosyalumnos.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)


@Entity
@Table(name ="CURSOS")
public class Curso implements Serializable{

	/**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	
	@Id
	@Column(name = "ID_CURSO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMBRE", length = 50, unique = true)
	private String nombre;
	
	@Column(name = "DESCRIPCION", length = 100)
	private String descripcion;
	
	@Column(name = "DURACION")
	private int duracion;

	@Builder.Default
	@OneToMany(mappedBy = "curso", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Alumno> alumnos = new ArrayList<>();
}
