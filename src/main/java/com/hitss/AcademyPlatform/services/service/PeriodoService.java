package com.hitss.AcademyPlatform.services.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hitss.AcademyPlatform.dto.periodo.PeriodoRequestDTO;
import com.hitss.AcademyPlatform.entities.PeriodoLectivo;

@Service
public interface PeriodoService {
	List<PeriodoLectivo> findAll();
	PeriodoLectivo save(PeriodoRequestDTO periodoDTO); 
	Optional<PeriodoLectivo> getById(Long id);


}
