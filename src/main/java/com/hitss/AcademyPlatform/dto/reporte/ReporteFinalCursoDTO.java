package com.hitss.AcademyPlatform.dto.reporte;

import lombok.Data;
import java.util.List;

@Data
public class ReporteFinalCursoDTO {
    private String nombreCurso;
    private List<AsignaturaConNotasDTO> asignaturas;
}

@Data	
class AsignaturaConNotasDTO { 
    private String nombreAsignatura;
    private List<Double> notas;
}