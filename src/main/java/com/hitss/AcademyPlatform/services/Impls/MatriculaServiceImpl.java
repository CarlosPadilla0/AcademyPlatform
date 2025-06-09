package com.hitss.AcademyPlatform.services.Impls;

import com.hitss.AcademyPlatform.dto.matricula.MatriculaRequestDTO;
import com.hitss.AcademyPlatform.entities.*; // Importar todas las entidades
import com.hitss.AcademyPlatform.repositories.*; // Importar todos los repositorios
import com.hitss.AcademyPlatform.services.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {

	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private EstudianteRepository estudianteRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private PeriodoRepository periodoRepository;

	@Override
	public Matricula createMatricula(MatriculaRequestDTO dto) {
		if (matriculaRepository.existsByEstudianteIdAndCursoIdAndPeriodoLectivoId(dto.getEstudianteId(), dto.getCursoId(), dto.getPeriodoId())) {
			throw new DataIntegrityViolationException(
					"El estudiante ya está matriculado en este curso para este periodo.");
		}

		Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
				.orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
		Curso curso = cursoRepository.findById(dto.getCursoId())
				.orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		PeriodoLectivo periodo = periodoRepository.findById(dto.getPeriodoId())
				.orElseThrow(() -> new RuntimeException("Periodo no encontrado"));

		Matricula nuevaMatricula = new Matricula();
		nuevaMatricula.setEstudiante(estudiante);
		nuevaMatricula.setCurso(curso);
		nuevaMatricula.setPeriodoLectivo(periodo);

		return matriculaRepository.save(nuevaMatricula);
	}

	@Override
	public List<Matricula> findAll() {
		return matriculaRepository.findAll();
	}

	@Override
	public Optional<Matricula> findById(Long id) {
		return matriculaRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		matriculaRepository.deleteById(id);
	}
}