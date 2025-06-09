package com.hitss.AcademyPlatform.dto.asignatura;

import com.hitss.AcademyPlatform.dto.curso.CursoResponseDTO;
import com.hitss.AcademyPlatform.dto.profesor.ProfesorResponseDTO;

import lombok.Data;

@Data
public class AsignaturaResponseDTO {
    private Long id;
    private String nombre;
    private ProfesorResponseDTO profesor; 
    private CursoResponseDTO curso;       
}