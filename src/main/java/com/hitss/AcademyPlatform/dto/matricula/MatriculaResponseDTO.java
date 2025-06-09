package com.hitss.AcademyPlatform.dto.matricula;

import com.hitss.AcademyPlatform.dto.curso.CursoResponseDTO;
import com.hitss.AcademyPlatform.dto.estudiante.EstudianteResponseDTO;
import com.hitss.AcademyPlatform.dto.periodo.PeriodoResponseDTO;

import lombok.Data;

@Data
public class MatriculaResponseDTO {
    private Long id;
    
    private EstudianteResponseDTO estudiante;
    private CursoResponseDTO curso;
    private PeriodoResponseDTO periodoLectivo; 
}