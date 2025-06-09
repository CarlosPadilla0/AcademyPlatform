package com.hitss.AcademyPlatform.entities;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @IsRequired

    private String nombre;


    @IsRequired
    private String anioAcademico;

    @JsonManagedReference("curso-asignatura") 
    @OneToMany(mappedBy = "curso")
    private List<Asignatura> asignaturas = new ArrayList<Asignatura>();

    @JsonManagedReference("curso-matricula")
    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas = new ArrayList<>();
}