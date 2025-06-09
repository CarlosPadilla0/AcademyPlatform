package com.hitss.AcademyPlatform.dto.estudiante;

import com.hitss.AcademyPlatform.dto.usuario.UsuarioResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudianteResponseDTO extends UsuarioResponseDTO {
    private String codigoMatricula;
}
