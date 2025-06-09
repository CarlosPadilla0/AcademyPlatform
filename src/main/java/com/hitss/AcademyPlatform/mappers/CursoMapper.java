package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.curso.CursoResponseDTO;
import com.hitss.AcademyPlatform.entities.Curso;
import java.util.List;
import java.util.stream.Collectors;

public class CursoMapper {

    public static CursoResponseDTO toResponseDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        CursoResponseDTO dto = new CursoResponseDTO();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setAnioAcademico(curso.getAnioAcademico());
        return dto;
    }

    public static List<CursoResponseDTO> toResponseDTOList(List<Curso> cursos) {
        return cursos.stream()
                .map(CursoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}