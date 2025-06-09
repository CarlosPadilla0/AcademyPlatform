package com.hitss.AcademyPlatform.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaRequestDTO;
import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaResponseDTO;
import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaUpdateRequestDTO;
import com.hitss.AcademyPlatform.entities.Asignatura;
import com.hitss.AcademyPlatform.mappers.AsignaturaMapper;
import com.hitss.AcademyPlatform.services.service.AsignaturaService;
import com.hitss.AcademyPlatform.utils.ValidationErrors;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {
	
    @Autowired
	private AsignaturaService asignaturaService;

	@PreAuthorize("hasRole('ADMIN')") 
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody AsignaturaRequestDTO asignaturaDTO, BindingResult result) {
		if(result.hasFieldErrors()) {
			return ValidationErrors.validation(result);
		}
        Asignatura asignaturaGuardada = asignaturaService.createAsignatura(asignaturaDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(AsignaturaMapper.toResponseDTO(asignaturaGuardada));
	}

	@PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')") 
	@GetMapping
	public List<AsignaturaResponseDTO> getAll() {
        List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
		return AsignaturaMapper.toResponseDTOList(asignaturas);
	}

	@PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
	@GetMapping("/{id}")
	public ResponseEntity<AsignaturaResponseDTO> getById(@PathVariable Long id) {
		return asignaturaService.getAsignaturaById(id)
				.map(AsignaturaMapper::toResponseDTO)
                .map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasAnyRole('ADMIN','TEACH-ER')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AsignaturaUpdateRequestDTO asignaturaDTO, BindingResult result) {
		if(result.hasFieldErrors()){
			return ValidationErrors.validation(result);
		}
		return asignaturaService.updateAsignatura(id, asignaturaDTO)
                .map(AsignaturaMapper::toResponseDTO)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<AsignaturaResponseDTO> delete(@PathVariable Long id) {
		return asignaturaService.deleteAsignatura(id)
                .map(AsignaturaMapper::toResponseDTO)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
