package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.matricula.MatriculaResponseDTO;
import com.hitss.AcademyPlatform.dto.periodo.PeriodoResponseDTO;
import com.hitss.AcademyPlatform.entities.Matricula;
import com.hitss.AcademyPlatform.entities.PeriodoLectivo;
import java.util.List;
import java.util.stream.Collectors;

public class MatriculaMapper {

    private static PeriodoResponseDTO toPeriodoResponseDTO(PeriodoLectivo periodo) {
        if (periodo == null) return null;
        PeriodoResponseDTO dto = new PeriodoResponseDTO();
        dto.setId(periodo.getId());
        dto.setNombre(periodo.getNombre());
        dto.setFechaInicio(periodo.getFechaInicio());
        dto.setFechaFin(periodo.getFechaFin());
        return dto;
    }

    public static MatriculaResponseDTO toResponseDTO(Matricula matricula) {
        if (matricula == null) return null;
        
        MatriculaResponseDTO dto = new MatriculaResponseDTO();
        dto.setId(matricula.getId());
        
        dto.setEstudiante(EstudianteMapper.toResponseDTO(matricula.getEstudiante()));
        dto.setCurso(CursoMapper.toResponseDTO(matricula.getCurso()));
        dto.setPeriodoLectivo(toPeriodoResponseDTO(matricula.getPeriodoLectivo()));
        
        return dto;
    }

    public static List<MatriculaResponseDTO> toResponseDTOList(List<Matricula> matriculas) {
        return matriculas.stream()
                .map(MatriculaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}