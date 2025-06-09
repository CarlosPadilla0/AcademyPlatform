package com.hitss.AcademyPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hitss.AcademyPlatform.services.AuthService;
import com.hitss.AcademyPlatform.dto.login.AuthDTO;
import com.hitss.AcademyPlatform.dto.usuario.UsuarioDTO;

import jakarta.validation.Valid; 

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<UsuarioDTO> register(@Valid @RequestBody AuthDTO.RegisterRequest registerRequest) {
		UsuarioDTO usuarioCreado = authService.register(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
	}

	@GetMapping("/me")
	public ResponseEntity<UsuarioDTO> getMe(Authentication authentication) {
		UsuarioDTO usuarioActual = authService.getMe(authentication);
		return ResponseEntity.ok(usuarioActual);
	}
}
