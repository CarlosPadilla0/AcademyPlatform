package com.hitss.AcademyPlatform.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hitss.AcademyPlatform.validations.IsRequired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante extends Usuario {
    @PrimaryKeyJoinColumn(name = "usuario_id") 

    @IsRequired
    @Column(unique = true)
    private String codigoMatricula;

    @OneToMany(mappedBy = "estudiante")
    private List<Matricula> matriculas;
    @JsonManagedReference("estudiante-nota") 
    @OneToMany(mappedBy = "estudiante")
    private List<Nota> notas;
}

