package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.estudiante.EstudianteResponseDTO;
import com.hitss.AcademyPlatform.entities.Estudiante;
import java.util.List;
import java.util.stream.Collectors;

public class EstudianteMapper {

    public static EstudianteResponseDTO toResponseDTO(Estudiante estudiante) {
        if (estudiante == null) {
            return null;
        }
        EstudianteResponseDTO dto = new EstudianteResponseDTO();
        
        dto.setId(estudiante.getId());
        dto.setNombre(estudiante.getNombre());
        dto.setEmail(estudiante.getEmail());
        dto.setRole(RoleMapper.toDTO(estudiante.getRole()));
        dto.setCodigoMatricula(estudiante.getCodigoMatricula());
        
        return dto;
    }

    public static List<EstudianteResponseDTO> toResponseDTOList(List<Estudiante> estudiantes) {
        return estudiantes.stream()
                .map(EstudianteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}