package com.hitss.AcademyPlatform.dto.usuario;


import com.hitss.AcademyPlatform.validations.IsRequired;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UsuarioUpdateRequestDTO {
    
    @IsRequired
    private String nombre;
    
    @IsRequired
    @Email
    private String email;

    private String password; 
}