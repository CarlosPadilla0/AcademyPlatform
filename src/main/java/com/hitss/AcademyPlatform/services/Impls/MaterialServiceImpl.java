package com.hitss.AcademyPlatform.services.Impls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.material.MaterialUploadRequestDTO;
import com.hitss.AcademyPlatform.entities.Asignatura;
import com.hitss.AcademyPlatform.entities.Material;
import com.hitss.AcademyPlatform.entities.Profesor;
import com.hitss.AcademyPlatform.repositories.AsignaturaRepository;
import com.hitss.AcademyPlatform.repositories.MaterialRepository;
import com.hitss.AcademyPlatform.repositories.ProfesorRepository;
import com.hitss.AcademyPlatform.services.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Autowired
    private ProfesorRepository profesorRepository;

    public Material uploadMaterial(MaterialUploadRequestDTO materialDTO, String profesorEmail) {
        Asignatura asignatura = asignaturaRepository.findById(materialDTO.getAsignaturaId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        
        Profesor profesor = profesorRepository.findByEmail(profesorEmail)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        Material nuevoMaterial = new Material();
        nuevoMaterial.setTitulo(materialDTO.getTitulo());
        nuevoMaterial.setDescripcion(materialDTO.getDescripcion());
        nuevoMaterial.setArchivoUrl(materialDTO.getArchivoUrl());
        nuevoMaterial.setAsignatura(asignatura);
        nuevoMaterial.setProfesor(profesor); 

		return materialRepository.save(nuevoMaterial);
	}

    @Override
	public List<Material> getMaterialsByAsignatura(Long subjectId) {
        return materialRepository.findByAsignaturaId(subjectId);
	}

	@Override
	@Transactional
	public Optional<Material> deleteMaterial(Long materialId) {
		Optional<Material> optionalMaterial = materialRepository.findById(materialId);
		optionalMaterial.ifPresent(materialRepository::delete);
		return optionalMaterial;
	}
}
