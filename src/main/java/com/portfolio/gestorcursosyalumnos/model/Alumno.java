package com.portfolio.gestorcursosyalumnos.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "ALUMNOS")
public class Alumno implements Serializable{

	/**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	
	@Id
	@Column(name = "ID_ALUMNO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMBRE", length = 50)
	private String nombre;

	@Column(name = "EMAIL", length = 50, unique = true)
	private String email;
	
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CURSO")
	private Curso curso;
}
