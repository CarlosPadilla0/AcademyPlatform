package com.hitss.AcademyPlatform.controllers;

import com.hitss.AcademyPlatform.dto.nota.NotaResponseDTO;
import com.hitss.AcademyPlatform.dto.reporte.ReportePromedioDTO;
import com.hitss.AcademyPlatform.services.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/notas-promedio")
    public List<ReportePromedioDTO> getNotasPromedio() {
        return reporteService.calcularNotasPromedio();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')") 
    @GetMapping("/historial-estudiante/{id}")
    public List<NotaResponseDTO> getHistorialEstudiante(@PathVariable Long id) {
        return reporteService.getHistorialEstudiante(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reporte-final/{cursoId}")
    public ResponseEntity<?> getReporteFinal(@PathVariable Long cursoId) {
        return ResponseEntity.ok(reporteService.getReporteFinal(cursoId));
    }
}