package com.hitss.AcademyPlatform.services.Impls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.curso.CursoCreateRequestDTO;
import com.hitss.AcademyPlatform.entities.Curso;
import com.hitss.AcademyPlatform.repositories.CursoRepository;
import com.hitss.AcademyPlatform.services.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {
	@Autowired
	private CursoRepository cursoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return cursoRepository.findAll();
	}

	@Override
	@Transactional
	public Curso save(CursoCreateRequestDTO cursoDTO) {
        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre(cursoDTO.getNombre());
        nuevoCurso.setAnioAcademico(cursoDTO.getAnioAcademico());
		return cursoRepository.save(nuevoCurso);
	}

	@Override
	@Transactional
	public Optional<Curso> getById(Long id) {
		return cursoRepository.findById(id);
	}
}
