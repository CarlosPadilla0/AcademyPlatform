package com.hitss.AcademyPlatform.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hitss.AcademyPlatform.validations.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asignaturas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@IsRequired

	private String nombre;
	@JsonBackReference("profesor-asignatura")
	@ManyToOne
	@JoinColumn(name = "profesor_id")
	private Profesor profesor;

	@JsonBackReference("curso-asignatura") 
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@JsonManagedReference("asignatura-nota")
	@OneToMany(mappedBy = "asignatura")
	private List<Nota> notas = new ArrayList<>();

	@JsonManagedReference("asignatura-material") 
	@OneToMany(mappedBy = "asignatura")
	private List<Material> materiales = new ArrayList<>();

}
