package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.material.MaterialResponseDTO;
import com.hitss.AcademyPlatform.entities.Material;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialMapper {

    public static MaterialResponseDTO toResponseDTO(Material material) {
        if (material == null) return null;
        
        MaterialResponseDTO dto = new MaterialResponseDTO();
        dto.setId(material.getId());
        dto.setTitulo(material.getTitulo());
        dto.setDescripcion(material.getDescripcion());
        dto.setArchivoUrl(material.getArchivoUrl());
        
        dto.setAsignatura(AsignaturaMapper.toSimpleResponseDTO(material.getAsignatura()));
        dto.setProfesor(ProfesorMapper.toSimpleResponseDTO(material.getProfesor()));
        
        return dto;
    }

    public static List<MaterialResponseDTO> toResponseDTOList(List<Material> materiales) {
        return materiales.stream()
                .map(MaterialMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}