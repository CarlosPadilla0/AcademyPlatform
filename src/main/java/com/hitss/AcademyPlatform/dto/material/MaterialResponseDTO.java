package com.hitss.AcademyPlatform.dto.material;

import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaSimpleResponseDTO;
import com.hitss.AcademyPlatform.dto.profesor.ProfesorSimpleResponseDTO;

import lombok.Data;

@Data
public class MaterialResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String archivoUrl;    
    private AsignaturaSimpleResponseDTO asignatura;
    private ProfesorSimpleResponseDTO profesor;
}