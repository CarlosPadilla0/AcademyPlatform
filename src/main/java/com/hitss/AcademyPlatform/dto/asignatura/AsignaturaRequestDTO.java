package com.hitss.AcademyPlatform.dto.asignatura;

import lombok.Data;

@Data
public class AsignaturaRequestDTO {
	private String nombre;
	private Long profesorId; 
	private Long cursoId;   
}