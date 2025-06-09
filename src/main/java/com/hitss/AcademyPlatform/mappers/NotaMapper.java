package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.nota.NotaResponseDTO;
import com.hitss.AcademyPlatform.entities.Nota;
import java.util.List;
import java.util.stream.Collectors;
public class NotaMapper {

    public static NotaResponseDTO toResponseDTO(Nota nota) {
        if (nota == null) {
            return null;
        }
        NotaResponseDTO dto = new NotaResponseDTO();
        dto.setId(nota.getId());
        dto.setValor(nota.getValor());
        dto.setObservaciones(nota.getObservaciones());
        return dto;
    }

    public static List<NotaResponseDTO> toResponseDTOList(List<Nota> notas) {
        return notas.stream()
                .map(NotaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}