package com.hitss.AcademyPlatform.services.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hitss.AcademyPlatform.dto.material.MaterialUploadRequestDTO;
import com.hitss.AcademyPlatform.entities.Material;

@Service
public interface MaterialService {
	Material uploadMaterial(MaterialUploadRequestDTO materialDTO, String profesorEmail);
	List<Material> getMaterialsByAsignatura(Long subjectId);
	Optional<Material> deleteMaterial(Long materialId);
}

