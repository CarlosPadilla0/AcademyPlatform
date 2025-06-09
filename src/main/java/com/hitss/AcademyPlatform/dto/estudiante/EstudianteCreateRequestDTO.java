package com.hitss.AcademyPlatform.dto.estudiante;

import com.hitss.AcademyPlatform.validations.IsRequired;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class EstudianteCreateRequestDTO {

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El email es requerido")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    @IsRequired
    private String codigoMatricula;
}