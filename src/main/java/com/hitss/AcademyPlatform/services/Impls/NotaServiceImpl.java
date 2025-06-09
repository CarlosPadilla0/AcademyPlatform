package com.hitss.AcademyPlatform.services.Impls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.nota.NotaRequestDTO;
import com.hitss.AcademyPlatform.entities.Asignatura;
import com.hitss.AcademyPlatform.entities.Estudiante;
import com.hitss.AcademyPlatform.entities.Nota;
import com.hitss.AcademyPlatform.entities.PeriodoLectivo;
import com.hitss.AcademyPlatform.repositories.AsignaturaRepository;
import com.hitss.AcademyPlatform.repositories.EstudianteRepository;
import com.hitss.AcademyPlatform.repositories.NotaRepository;
import com.hitss.AcademyPlatform.repositories.PeriodoRepository;
import com.hitss.AcademyPlatform.services.service.NotaService;

@Service
public class NotaServiceImpl implements NotaService {
	@Autowired
	private NotaRepository notaRepository;
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	@Autowired
	private EstudianteRepository estudianteRepository;
	@Autowired
    private PeriodoRepository periodoRepository; 


	@Override
	@Transactional
	public Nota registrarNota(NotaRequestDTO notaDTO) {
        Estudiante estudiante = estudianteRepository.findById(notaDTO.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + notaDTO.getEstudianteId()));
        
        Asignatura asignatura = asignaturaRepository.findById(notaDTO.getAsignaturaId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada con id: " + notaDTO.getAsignaturaId()));
        
        PeriodoLectivo periodo = periodoRepository.findById(notaDTO.getPeriodoId())
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado con id: " + notaDTO.getPeriodoId()));

        Nota nuevaNota = new Nota();
        nuevaNota.setValor(notaDTO.getValor());
        nuevaNota.setObservaciones(notaDTO.getObservaciones());
        nuevaNota.setEstudiante(estudiante);
        nuevaNota.setAsignatura(asignatura);
        nuevaNota.setPeriodo(periodo);

        return notaRepository.save(nuevaNota);
    }

	@Override
	public List<Nota> obtenerNotasPorAsignatura(Long asignaturaId) {
		Optional<Asignatura> optionalAsignatura = asignaturaRepository.findById(asignaturaId);
		return optionalAsignatura.map(Asignatura::getNotas).orElse(List.of());
	}

	@Override
	public List<Nota> obtenerNotasPorEstudiante(Long estudianteId) {
		Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(estudianteId);
		return optionalEstudiante.map(Estudiante::getNotas).orElse(List.of());
	}


	@Transactional
	@Override
	public Optional<Nota> editarNota(Long id, Nota nota) {
		Optional<Nota> optionalNota = notaRepository.findById(id);
		if(optionalNota.isPresent()) {
			Nota existingNota = optionalNota.get();
			existingNota.setValor(nota.getValor());
			existingNota.setObservaciones(nota.getObservaciones());
			existingNota.setPeriodo(nota.getPeriodo());


			return Optional.of(notaRepository.save(existingNota));
		}

		return optionalNota;
	}

	@Transactional
	@Override
	public Optional<Nota> eliminarNota(Long id) {
		Optional<Nota> optionalNota = notaRepository.findById(id);
		optionalNota.ifPresent(notaRepository::delete);
		return optionalNota;


	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Nota> findById(Long id) {
		return notaRepository.findById(id);
	}



}
