package com.hitss.AcademyPlatform.controllers;

import java.util.List;
import java.util.Optional;

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

import com.hitss.AcademyPlatform.dto.nota.NotaRequestDTO;
import com.hitss.AcademyPlatform.entities.Nota;
import com.hitss.AcademyPlatform.services.service.NotaService;
import com.hitss.AcademyPlatform.utils.ValidationErrors;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PostMapping
    public ResponseEntity<?> registrarNota(@Valid @RequestBody NotaRequestDTO notaDTO, //
            BindingResult result) {
        if (result.hasFieldErrors()) {
            return ValidationErrors.validation(result);
        }
        
        Nota nuevaNota = notaService.registrarNota(notaDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNota);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENS')")
    @GetMapping("/asignatura/{id}")
    public List<Nota> getNotasPorAsignatura(@PathVariable Long id) {
        return notaService.obtenerNotasPorAsignatura(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENS')")
    @GetMapping("/estudiante/{id}")
    public List<Nota> getNotasPorEstudiante(@PathVariable Long id) {
        return notaService.obtenerNotasPorEstudiante(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> editarNota(@Valid @PathVariable Long id, @RequestBody Nota nota,
    		BindingResult result) {
    	if(result.hasFieldErrors()) {
    		return ValidationErrors.validation(result);
    	}
    	Optional<Nota> notaOptional = notaService.findById(id);

    	if(notaOptional.isPresent()){
    		return ResponseEntity.ok(notaService.editarNota(id, nota));
    	}

		return ResponseEntity.notFound().build();

    }
    @PreAuthorize("hasAnyRole('ADMIN')")

    @DeleteMapping("/{id}")
    public ResponseEntity<Nota> eliminarNota(@PathVariable Long id) {
        return notaService.eliminarNota(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
