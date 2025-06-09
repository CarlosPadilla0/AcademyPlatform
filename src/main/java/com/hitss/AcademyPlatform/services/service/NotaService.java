package com.hitss.AcademyPlatform.services.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hitss.AcademyPlatform.dto.nota.NotaRequestDTO;
import com.hitss.AcademyPlatform.entities.Nota;

@Service
public interface NotaService {
    Nota registrarNota(NotaRequestDTO notaDTO);
	List<Nota> obtenerNotasPorAsignatura(Long asignaturaId);
	List<Nota> obtenerNotasPorEstudiante(Long estudianteId);
	Optional<Nota> editarNota(Long id, Nota nota);
	Optional<Nota> eliminarNota(Long id);
	Optional<Nota> findById(Long id);

}
