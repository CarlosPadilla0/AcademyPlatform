package com.hitss.AcademyPlatform.controllers;

import com.hitss.AcademyPlatform.dto.estudiante.EstudianteCreateRequestDTO;
import com.hitss.AcademyPlatform.dto.estudiante.EstudianteResponseDTO;
import com.hitss.AcademyPlatform.dto.nota.NotaResponseDTO;
import com.hitss.AcademyPlatform.entities.Estudiante;
import com.hitss.AcademyPlatform.entities.Nota;
import com.hitss.AcademyPlatform.mappers.EstudianteMapper;
import com.hitss.AcademyPlatform.mappers.NotaMapper;
import com.hitss.AcademyPlatform.services.service.EstudianteService;
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
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EstudianteCreateRequestDTO estudianteDTO, BindingResult result) {
        if(result.hasFieldErrors()) {
            return ValidationErrors.validation(result);
        }
        Estudiante estudianteGuardado = estudianteService.saveEstudiante(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EstudianteMapper.toResponseDTO(estudianteGuardado));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<EstudianteResponseDTO> getAll() {
        List<Estudiante> estudiantes = estudianteService.findAllEstudiantes();
        return EstudianteMapper.toResponseDTOList(estudiantes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> getById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id)
                .map(EstudianteMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')") 
    @GetMapping("/{id}/notas")
    public ResponseEntity<List<NotaResponseDTO>> getNotas(@PathVariable Long id) {
        List<Nota> notas = estudianteService.getNotasByEstudianteId(id);
        return ResponseEntity.ok(NotaMapper.toResponseDTOList(notas));
    }
}