package com.hitss.AcademyPlatform.controllers;

import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaSimpleResponseDTO;
import com.hitss.AcademyPlatform.dto.profesor.ProfesorCreateRequestDTO;
import com.hitss.AcademyPlatform.dto.profesor.ProfesorResponseDTO;
import com.hitss.AcademyPlatform.entities.Profesor;
import com.hitss.AcademyPlatform.mappers.AsignaturaMapper;
import com.hitss.AcademyPlatform.mappers.ProfesorMapper;
import com.hitss.AcademyPlatform.services.service.ProfesorService;
import com.hitss.AcademyPlatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
	
    @Autowired
	private ProfesorService profesorService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody ProfesorCreateRequestDTO profesorDTO, BindingResult result) {
		if(result.hasFieldErrors()) {
			return ValidationErrors.validation(result);
		}
		Profesor profesorGuardado = profesorService.save(profesorDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProfesorMapper.toResponseDTO(profesorGuardado));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<ProfesorResponseDTO> getAll() {
        List<Profesor> profesores = profesorService.getAllProfesores();
		return ProfesorMapper.toResponseDTOList(profesores);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ProfesorResponseDTO> getById(@PathVariable Long id) {
		return profesorService.getProfesorById(id)
				.map(ProfesorMapper::toResponseDTO)
                .map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
    
	@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
	@GetMapping("/{id}/asignaturas")
	public ResponseEntity<List<AsignaturaSimpleResponseDTO>> getAsignaturas(@PathVariable Long id) {
		return profesorService.getProfesorById(id)
				.map(profesor -> ResponseEntity.ok(AsignaturaMapper.toSimpleResponseDTOList(profesor.getAsignaturas())))
				.orElse(ResponseEntity.notFound().build());
	}
}