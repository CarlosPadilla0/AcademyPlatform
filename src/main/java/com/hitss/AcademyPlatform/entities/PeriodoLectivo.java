package com.hitss.AcademyPlatform.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hitss.AcademyPlatform.validations.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "periodos_lectivos")
@Getter
@Setter
public class PeriodoLectivo {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@IsRequired
	private String nombre;

    @NotNull 	
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

	// Relación con Nota
	@JsonManagedReference("periodo-nota") 
	@OneToMany(mappedBy = "periodo")
	private List<Nota> notas = new ArrayList<Nota>(); 

	@JsonManagedReference("periodo-matricula") 
	@OneToMany(mappedBy = "periodoLectivo")
	private List<Matricula> matriculas = new ArrayList<>(); 
}

