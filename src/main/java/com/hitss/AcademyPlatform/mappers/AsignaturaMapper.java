package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaResponseDTO;
import com.hitss.AcademyPlatform.dto.asignatura.AsignaturaSimpleResponseDTO;
import com.hitss.AcademyPlatform.entities.Asignatura;
import java.util.List;
import java.util.stream.Collectors;

public class AsignaturaMapper {

	public static AsignaturaResponseDTO toResponseDTO(Asignatura asignatura) {
        if (asignatura == null) {
            return null;
        }
        AsignaturaResponseDTO dto = new AsignaturaResponseDTO();
        dto.setId(asignatura.getId());
        dto.setNombre(asignatura.getNombre());
        
        dto.setProfesor(ProfesorMapper.toResponseDTO(asignatura.getProfesor()));
        dto.setCurso(CursoMapper.toResponseDTO(asignatura.getCurso()));
        
        return dto;
    }

    public static List<AsignaturaResponseDTO> toResponseDTOList(List<Asignatura> asignaturas) {
        return asignaturas.stream()
                .map(AsignaturaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public static AsignaturaSimpleResponseDTO toSimpleResponseDTO(Asignatura asignatura) {
        if (asignatura == null) {
            return null;
        }
        AsignaturaSimpleResponseDTO dto = new AsignaturaSimpleResponseDTO();
        dto.setId(asignatura.getId());
        dto.setNombre(asignatura.getNombre());
        return dto;
    }

    public static List<AsignaturaSimpleResponseDTO> toSimpleResponseDTOList(List<Asignatura> asignaturas) {
        return asignaturas.stream()
                .map(AsignaturaMapper::toSimpleResponseDTO)
                .collect(Collectors.toList());
    }
}