package com.hitss.AcademyPlatform.services.Impls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.periodo.PeriodoRequestDTO;
import com.hitss.AcademyPlatform.entities.PeriodoLectivo;
import com.hitss.AcademyPlatform.repositories.PeriodoRepository;
import com.hitss.AcademyPlatform.services.service.PeriodoService;

@Service
public class PeriodoServiceImpl implements PeriodoService {
	@Autowired
	private PeriodoRepository periodoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<PeriodoLectivo> findAll() {
		return periodoRepository.findAll();
	}

	@Override
	@Transactional
	public PeriodoLectivo save(PeriodoRequestDTO periodoDTO)  {
		PeriodoLectivo periodo = new PeriodoLectivo();
		periodo.setNombre(periodoDTO.getNombre());
		periodo.setFechaInicio(periodoDTO.getFechaInicio());
		periodo.setFechaFin(periodoDTO.getFechaFin());

		return periodoRepository.save(periodo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PeriodoLectivo> getById(Long id) {
		return periodoRepository.findById(id);
	}
}
