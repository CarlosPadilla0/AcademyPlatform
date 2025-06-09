package com.hitss.AcademyPlatform.services.service;

import com.hitss.AcademyPlatform.dto.*;
import com.hitss.AcademyPlatform.dto.nota.NotaResponseDTO;
import com.hitss.AcademyPlatform.dto.reporte.ReporteFinalCursoDTO;
import com.hitss.AcademyPlatform.dto.reporte.ReportePromedioDTO;

import java.util.List;

public interface ReporteService {
    List<ReportePromedioDTO> calcularNotasPromedio();
    List<NotaResponseDTO> getHistorialEstudiante(Long estudianteId);
    ReporteFinalCursoDTO getReporteFinal(Long cursoId);
}