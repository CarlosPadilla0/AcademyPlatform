package com.hitss.AcademyPlatform.controllers;

import com.hitss.AcademyPlatform.dto.curso.CursoCreateRequestDTO;
import com.hitss.AcademyPlatform.dto.curso.CursoResponseDTO;
import com.hitss.AcademyPlatform.entities.Curso;
import com.hitss.AcademyPlatform.mappers.CursoMapper;
import com.hitss.AcademyPlatform.services.service.CursoService;
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
@RequestMapping("/api/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CursoCreateRequestDTO cursoDTO, BindingResult result) {
    	if(result.hasFieldErrors()) {
			return ValidationErrors.validation(result);
		}
        Curso cursoGuardado = cursoService.save(cursoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
				.body(CursoMapper.toResponseDTO(cursoGuardado));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping
    public List<CursoResponseDTO> getAll() {
        List<Curso> cursos = cursoService.findAll();
        return CursoMapper.toResponseDTOList(cursos);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> getById(@PathVariable Long id) {
        return cursoService.getById(id)
                .map(CursoMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}