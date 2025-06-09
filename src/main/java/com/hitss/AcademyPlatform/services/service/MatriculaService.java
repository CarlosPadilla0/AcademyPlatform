package com.hitss.AcademyPlatform.services.service;

import com.hitss.AcademyPlatform.dto.matricula.MatriculaRequestDTO;
import com.hitss.AcademyPlatform.entities.Matricula;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public interface MatriculaService {
    Matricula createMatricula(MatriculaRequestDTO matriculaDTO);
    List<Matricula> findAll();
    Optional<Matricula> findById(Long id);
    void deleteById(Long id);
}