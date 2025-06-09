package com.hitss.AcademyPlatform.services.Impls;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.login.AuthDTO;
import com.hitss.AcademyPlatform.dto.usuario.UsuarioDTO;
import com.hitss.AcademyPlatform.entities.Estudiante;
import com.hitss.AcademyPlatform.entities.Profesor;
import com.hitss.AcademyPlatform.entities.Role;
import com.hitss.AcademyPlatform.entities.Usuario;
import com.hitss.AcademyPlatform.repositories.RoleRepository;
import com.hitss.AcademyPlatform.repositories.UserRepository;
import com.hitss.AcademyPlatform.security.TokenJWTConfig; 
import com.hitss.AcademyPlatform.services.AuthService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	@Transactional(readOnly = true)
	public AuthDTO.AuthResponse login(AuthDTO.LoginRequest loginRequest) {
		org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Usuario user = (Usuario) authentication.getPrincipal();

		Claims claims = Jwts.claims()
				.add("role", user.getRole().getName())
				.add("username", user.getEmail())
				.build();

		String token = Jwts.builder()
				.subject(user.getEmail())
				.claims(claims)
				.expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
				.issuedAt(new Date())
				.signWith(TokenJWTConfig.SECRET_KEY)
				.compact();

		return new AuthDTO.AuthResponse(token);
	}

	@Override
	@Transactional
	public UsuarioDTO register(AuthDTO.RegisterRequest registerRequest) {
		if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
			throw new RuntimeException("El email ya está registrado");
		}

		Role rol = roleRepository.findByName(registerRequest.getRol())
				.orElseThrow(() -> new RuntimeException("Rol no válido: " + registerRequest.getRol()));

		Usuario nuevoUsuario;

		if ("STUDENT".equalsIgnoreCase(rol.getName())) {
			Estudiante estudiante = new Estudiante();
			if (registerRequest.getCodigoMatricula() == null || registerRequest.getCodigoMatricula().isBlank()) {
				throw new IllegalArgumentException("El código de matrícula es requerido para el rol STUDENT");
			}
			estudiante.setCodigoMatricula(registerRequest.getCodigoMatricula());
			nuevoUsuario = estudiante;

		} else if ("TEACHER".equalsIgnoreCase(rol.getName())) {
			Profesor profesor = new Profesor();
			if (registerRequest.getEspecialidad() == null || registerRequest.getEspecialidad().isBlank()) {
				throw new IllegalArgumentException("La especialidad es requerida para el rol TEACHER");
			}
			profesor.setEspecialidad(registerRequest.getEspecialidad());
			nuevoUsuario = profesor;

		} else {
			nuevoUsuario = new Usuario();
		}

		nuevoUsuario.setNombre(registerRequest.getNombre());
		nuevoUsuario.setEmail(registerRequest.getEmail());
		nuevoUsuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		nuevoUsuario.setRole(rol);

		Usuario usuarioGuardado = userRepository.save(nuevoUsuario);

		return new UsuarioDTO(usuarioGuardado.getId(), usuarioGuardado.getNombre(), usuarioGuardado.getEmail(), usuarioGuardado.getRole().getName());
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO getMe(org.springframework.security.core.Authentication authentication) {
		if (authentication == null || !authentication.isAuthenticated()) {
			throw new RuntimeException("No autenticado");
		}
		String email = (String) authentication.getPrincipal(); 
		Usuario user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado en la base de datos, pero presente en el token: " + email));

		return new UsuarioDTO(user.getId(), user.getNombre(), user.getEmail(), user.getRole().getName());
	}
}