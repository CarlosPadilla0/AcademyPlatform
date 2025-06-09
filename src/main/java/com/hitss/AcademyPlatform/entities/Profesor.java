package com.hitss.AcademyPlatform.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hitss.AcademyPlatform.validations.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profesores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profesor extends Usuario {
    
    @PrimaryKeyJoinColumn(name = "usuario_id")

    @IsRequired
    private String especialidad;
    @JsonManagedReference
    @OneToMany(mappedBy = "profesor")
    private List<Asignatura> asignaturas;
}

