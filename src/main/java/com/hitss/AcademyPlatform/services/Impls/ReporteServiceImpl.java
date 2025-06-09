package com.hitss.AcademyPlatform.services.Impls;

import com.hitss.AcademyPlatform.dto.*;
import com.hitss.AcademyPlatform.dto.nota.NotaResponseDTO;
import com.hitss.AcademyPlatform.dto.reporte.ReporteFinalCursoDTO;
import com.hitss.AcademyPlatform.dto.reporte.ReportePromedioDTO;
import com.hitss.AcademyPlatform.entities.Nota;
import com.hitss.AcademyPlatform.mappers.NotaMapper;
import com.hitss.AcademyPlatform.repositories.NotaRepository; // Importar
import com.hitss.AcademyPlatform.services.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private NotaRepository notaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReportePromedioDTO> calcularNotasPromedio() {
      
        return notaRepository.findAllWithDetails().stream()
                .collect(Collectors.groupingBy(
                    nota -> nota.getAsignatura().getCurso().getNombre() + " - " + nota.getAsignatura().getNombre(),
                    Collectors.averagingDouble(Nota::getValor)
                ))
                .entrySet().stream()
                .map(entry -> {
                    String[] parts = entry.getKey().split(" - ");
                    return new ReportePromedioDTO(parts[0], parts[1], entry.getValue());
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotaResponseDTO> getHistorialEstudiante(Long estudianteId) {
        List<Nota> notas = notaRepository.findByEstudianteId(estudianteId);
        return NotaMapper.toResponseDTOList(notas);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ReporteFinalCursoDTO getReporteFinal(Long cursoId) {
        throw new UnsupportedOperationException("No implementado aún.");
    }
}