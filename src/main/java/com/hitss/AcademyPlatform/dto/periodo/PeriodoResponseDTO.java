package com.hitss.AcademyPlatform.dto.periodo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PeriodoResponseDTO {
    private Long id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}