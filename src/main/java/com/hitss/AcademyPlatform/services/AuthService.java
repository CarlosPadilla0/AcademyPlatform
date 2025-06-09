package com.hitss.AcademyPlatform.services;

import org.springframework.security.core.Authentication;

import com.hitss.AcademyPlatform.dto.login.AuthDTO;
import com.hitss.AcademyPlatform.dto.usuario.UsuarioDTO;

public interface AuthService {
	AuthDTO.AuthResponse login(AuthDTO.LoginRequest loginRequest);
	UsuarioDTO register(AuthDTO.RegisterRequest registerRequest);
	UsuarioDTO getMe(Authentication authentication);
}