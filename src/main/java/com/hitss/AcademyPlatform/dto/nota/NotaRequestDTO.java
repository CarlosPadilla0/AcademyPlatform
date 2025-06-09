package com.hitss.AcademyPlatform.dto.nota;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaRequestDTO {

    @NotNull(message = "El valor de la nota no puede ser nulo")
    @DecimalMin(value = "0.0", message = "La nota no puede ser menor que 0")
    @DecimalMax(value = "100.0", message = "La nota no puede ser mayor que 100")
    private Double valor;
    
    private String observaciones;

    @NotNull(message = "El ID del estudiante es requerido")
    private Long estudianteId;

    @NotNull(message = "El ID de la asignatura es requerido")
    private Long asignaturaId;

    @NotNull(message = "El ID del periodo es requerido")
    private Long periodoId;
}