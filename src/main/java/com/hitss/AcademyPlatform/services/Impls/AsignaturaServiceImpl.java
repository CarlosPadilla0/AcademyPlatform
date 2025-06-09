package com.hitss.AcademyPlatform.services.Impls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaRequestDTO;
import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaUpdateRequestDTO;
import com.hitss.AcademyPlatform.entities.Asignatura;
import com.hitss.AcademyPlatform.entities.Curso;
import com.hitss.AcademyPlatform.entities.Profesor;
import com.hitss.AcademyPlatform.repositories.AsignaturaRepository;
import com.hitss.AcademyPlatform.repositories.CursoRepository;
import com.hitss.AcademyPlatform.repositories.ProfesorRepository;
import com.hitss.AcademyPlatform.services.service.AsignaturaService;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {
	@Autowired
	private AsignaturaRepository asignaturaRepository;

	@Autowired
	private ProfesorRepository profesorRepository; 
	@Autowired
	private CursoRepository cursoRepository;  


	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> getAllAsignaturas() {
		return asignaturaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Asignatura> getAsignaturaById(Long id) {
		return asignaturaRepository.findById(id);
	}

	@Override
	@Transactional
	public Asignatura createAsignatura(AsignaturaRequestDTO asignaturaDTO) {
		Profesor profesor = profesorRepository.findById(asignaturaDTO.getProfesorId())
				.orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

		// 2. BUSCAMOS AL CURSO POR ID
		Curso curso = cursoRepository.findById(asignaturaDTO.getCursoId())
				.orElseThrow(() -> new RuntimeException("Curso no encontrado"));

		// 3. Creamos la entidad y la guardamos
		Asignatura nuevaAsignatura = new Asignatura();
		nuevaAsignatura.setNombre(asignaturaDTO.getNombre());
		nuevaAsignatura.setProfesor(profesor); // Asignamos el objeto completo
		nuevaAsignatura.setCurso(curso);       // Asignamos el objeto completo

		return asignaturaRepository.save(nuevaAsignatura);
	}


	@Override
	@Transactional
	public Optional<Asignatura> updateAsignatura(Long id, AsignaturaUpdateRequestDTO asignaturaDTO) {
		return asignaturaRepository.findById(id).map(existingAsignatura -> {
            Profesor profesor = profesorRepository.findById(asignaturaDTO.getProfesorId())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
            Curso curso = cursoRepository.findById(asignaturaDTO.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

            existingAsignatura.setNombre(asignaturaDTO.getNombre());
            existingAsignatura.setProfesor(profesor);
            existingAsignatura.setCurso(curso);
            
            return asignaturaRepository.save(existingAsignatura);
        });
	}

	@Override
	@Transactional
	public Optional<Asignatura> deleteAsignatura(Long id) {
		Optional<Asignatura> optionalAsignatura = asignaturaRepository.findById(id);
		optionalAsignatura.ifPresent(asignaturaRepository::delete);
		return optionalAsignatura;

	}




}
