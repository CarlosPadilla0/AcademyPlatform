package com.hitss.AcademyPlatform.services.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hitss.AcademyPlatform.dto.curso.CursoCreateRequestDTO;
import com.hitss.AcademyPlatform.entities.Curso;

@Service
public interface CursoService {

	List<Curso> findAll();
	Curso save(CursoCreateRequestDTO cursoDTO);
	Optional<Curso> getById(Long id);

}
