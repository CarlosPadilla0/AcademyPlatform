package com.hitss.AcademyPlatform.dto.asignatura;

import com.hitss.AcademyPlatform.validations.IsRequired;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsignaturaUpdateRequestDTO {

    @IsRequired
    private String nombre;

    @NotNull(message = "El ID del profesor es requerido")
    private Long profesorId;

    @NotNull(message = "El ID del curso es requerido")
    private Long cursoId;
}