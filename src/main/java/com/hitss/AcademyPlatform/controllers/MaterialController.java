package com.hitss.AcademyPlatform.controllers;

import com.hitss.AcademyPlatform.dto.material.MaterialResponseDTO;
import com.hitss.AcademyPlatform.dto.material.MaterialUploadRequestDTO;
import com.hitss.AcademyPlatform.entities.Material;
import com.hitss.AcademyPlatform.mappers.MaterialMapper;
import com.hitss.AcademyPlatform.services.service.MaterialService;
import com.hitss.AcademyPlatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication; // Importar
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {
    
    @Autowired
    private MaterialService materialService;

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PostMapping
    public ResponseEntity<?> upload(@Valid @RequestBody MaterialUploadRequestDTO materialDTO, BindingResult result, Authentication authentication) {
    	if(result.hasFieldErrors()) {
    		return ValidationErrors.validation(result);
    	}
        String profesorEmail = authentication.getName();
        Material materialGuardado = materialService.uploadMaterial(materialDTO, profesorEmail);
        
        return ResponseEntity.status(HttpStatus.CREATED)
        	.body(MaterialMapper.toResponseDTO(materialGuardado));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')") 
    @GetMapping("/asignatura/{id}")
    public List<MaterialResponseDTO> getByAsignatura(@PathVariable Long id) {
        List<Material> materiales = materialService.getMaterialsByAsignatura(id);
        return MaterialMapper.toResponseDTOList(materiales);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> delete(@PathVariable Long id) {
        return materialService.deleteMaterial(id)
                .map(MaterialMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}