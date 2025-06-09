package com.hitss.AcademyPlatform.dto.curso;

import com.hitss.AcademyPlatform.validations.IsRequired;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoCreateRequestDTO {

    @IsRequired
    private String nombre;
    
    @NotBlank(message = "El año académico no puede estar vacío")
    private String anioAcademico;
}