package com.hitss.AcademyPlatform.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hitss.AcademyPlatform.validations.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    @IsRequired

    private String observaciones;
    @JsonBackReference("estudiante-nota") 
    @NotNull(message = "El estudiante no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;
    @JsonBackReference("asignatura-nota") 
    @NotNull(message = "La asignatura no puede ser nula")
    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @JsonBackReference("periodo-nota") 
    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private PeriodoLectivo periodo;

}
