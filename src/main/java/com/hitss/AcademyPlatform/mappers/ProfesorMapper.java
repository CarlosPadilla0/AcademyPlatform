package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.profesor.ProfesorResponseDTO;
import com.hitss.AcademyPlatform.dto.profesor.ProfesorSimpleResponseDTO;
import com.hitss.AcademyPlatform.entities.Profesor;
import java.util.List;
import java.util.stream.Collectors;

public class ProfesorMapper {

    public static ProfesorResponseDTO toResponseDTO(Profesor profesor) {
        if (profesor == null) {
            return null;
        }
        ProfesorResponseDTO dto = new ProfesorResponseDTO();
        
        dto.setId(profesor.getId());
        dto.setNombre(profesor.getNombre());
        dto.setEmail(profesor.getEmail());
        dto.setRole(RoleMapper.toDTO(profesor.getRole())); 
        dto.setEspecialidad(profesor.getEspecialidad());
        
        return dto;
    }

    public static List<ProfesorResponseDTO> toResponseDTOList(List<Profesor> profesores) {
        return profesores.stream()
                .map(ProfesorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    public static ProfesorSimpleResponseDTO toSimpleResponseDTO(Profesor profesor) {
        if (profesor == null) return null;
        ProfesorSimpleResponseDTO dto = new ProfesorSimpleResponseDTO();
        dto.setId(profesor.getId());
        dto.setNombre(profesor.getNombre());
        return dto;
    }
}