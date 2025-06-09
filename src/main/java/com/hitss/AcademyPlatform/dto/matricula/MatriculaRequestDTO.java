package com.hitss.AcademyPlatform.dto.matricula;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatriculaRequestDTO {

    @NotNull(message = "El ID del estudiante es requerido")
    private Long estudianteId;

    @NotNull(message = "El ID del curso es requerido")
    private Long cursoId;

    @NotNull(message = "El ID del periodo lectivo es requerido")
    private Long periodoId;
}