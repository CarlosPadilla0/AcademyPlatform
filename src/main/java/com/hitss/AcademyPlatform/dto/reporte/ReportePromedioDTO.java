package com.hitss.AcademyPlatform.dto.reporte;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class ReportePromedioDTO {
    private String curso;
    private String asignatura;
    private Double promedio;
}