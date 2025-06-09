package com.hitss.AcademyPlatform.entities;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hitss.AcademyPlatform.validations.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "materiales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @IsRequired

    private String titulo;
    @IsRequired

    private String descripcion;
    @IsRequired

    @URL
    private String archivoUrl;

    @JsonBackReference("asignatura-material")
    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    @JsonBackReference("profesor-material")
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;
}
