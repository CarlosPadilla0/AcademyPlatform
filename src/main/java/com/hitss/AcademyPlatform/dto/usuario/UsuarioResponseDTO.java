package com.hitss.AcademyPlatform.dto.usuario;

import com.hitss.AcademyPlatform.dto.role.RoleDTO;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private RoleDTO role;
}