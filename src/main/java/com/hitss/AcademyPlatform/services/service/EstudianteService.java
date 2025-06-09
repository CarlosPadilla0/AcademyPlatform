package com.hitss.AcademyPlatform.services.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hitss.AcademyPlatform.dto.estudiante.EstudianteCreateRequestDTO;
import com.hitss.AcademyPlatform.entities.Estudiante;
import com.hitss.AcademyPlatform.entities.Nota;

@Service
public interface EstudianteService {
	List<Estudiante> findAllEstudiantes();
	Estudiante saveEstudiante(EstudianteCreateRequestDTO estudianteDTO);
	Optional<Estudiante> getEstudianteById(Long id);
	List<Nota> getNotasByEstudianteId(Long id);
}
