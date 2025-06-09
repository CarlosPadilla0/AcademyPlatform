package com.hitss.AcademyPlatform.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
	private Long id;
	private String nombre;
	private String email;
	private String rol;
}
