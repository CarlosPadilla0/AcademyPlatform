package com.hitss.AcademyPlatform.dto.material;

import com.hitss.AcademyPlatform.validations.IsRequired;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import lombok.Data;

@Data
public class MaterialUploadRequestDTO {

    @IsRequired
    private String titulo;

    private String descripcion;

    @IsRequired
    @URL(message = "Debe ser una URL válida")
    private String archivoUrl;

    @NotNull(message = "El ID de la asignatura es requerido")
    private Long asignaturaId;
}