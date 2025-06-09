package com.hitss.AcademyPlatform.services.service;

import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaRequestDTO;
import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaUpdateRequestDTO;
import com.hitss.AcademyPlatform.entities.Asignatura;
import java.util.List;
import java.util.Optional;

public interface AsignaturaService {
	List<Asignatura> getAllAsignaturas();
	Optional<Asignatura> getAsignaturaById(Long id);
	Asignatura createAsignatura(AsignaturaRequestDTO asignaturaDTO);
	Optional<Asignatura> updateAsignatura(Long id, AsignaturaUpdateRequestDTO asignaturaDTO);
	Optional<Asignatura> deleteAsignatura(Long id);
}
