package com.hitss.AcademyPlatform.services.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hitss.AcademyPlatform.dto.profesor.ProfesorCreateRequestDTO;
import com.hitss.AcademyPlatform.entities.Asignatura;
import com.hitss.AcademyPlatform.entities.Profesor;

@Service
public interface ProfesorService {
	List<Profesor> getAllProfesores();
	Profesor save(ProfesorCreateRequestDTO profesorDTO);
	Optional<Profesor> getProfesorById(Long id);
	List<Asignatura> getAsignaturasAsignadas(Long profesorId);
}
