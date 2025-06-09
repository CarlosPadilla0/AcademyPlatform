package com.hitss.AcademyPlatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.AcademyPlatform.dto.periodo.PeriodoRequestDTO;
import com.hitss.AcademyPlatform.entities.PeriodoLectivo;
import com.hitss.AcademyPlatform.services.service.PeriodoService;
import com.hitss.AcademyPlatform.utils.ValidationErrors;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/periodos")
public class PeriodoController {
    @Autowired
    private PeriodoService periodoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PeriodoRequestDTO periodoDTO, BindingResult result) {
    	if (result.hasFieldErrors()) {
			return ValidationErrors.validation(result);
		}
        return ResponseEntity.status(HttpStatus.CREATED)
				.body(periodoService.save(periodoDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<PeriodoLectivo> getAll() {
        return periodoService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PeriodoLectivo> getById(@PathVariable Long id) {
        return periodoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
