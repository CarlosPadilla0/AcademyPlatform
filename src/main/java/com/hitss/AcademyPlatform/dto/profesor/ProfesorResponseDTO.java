package com.hitss.AcademyPlatform.dto.profesor;

import com.hitss.AcademyPlatform.dto.usuario.UsuarioResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorResponseDTO extends UsuarioResponseDTO {
    private String especialidad;
}