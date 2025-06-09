package com.hitss.AcademyPlatform.controllers;

import com.hitss.AcademyPlatform.dto.usuario.UsuarioResponseDTO;
import com.hitss.AcademyPlatform.dto.usuario.UsuarioUpdateRequestDTO;
import com.hitss.AcademyPlatform.entities.Usuario;
import com.hitss.AcademyPlatform.mappers.UserMapper;
import com.hitss.AcademyPlatform.services.service.UserService;
import com.hitss.AcademyPlatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
    @Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<UsuarioResponseDTO> getAll() {
		List<Usuario> usuarios = userService.findAllUsers();
        return UserMapper.toResponseDTOList(usuarios);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
		return userService.getUserById(id)
            .map(UserMapper::toResponseDTO) 
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateRequestDTO userDTO, BindingResult result) {
		if(result.hasFieldErrors()) {
			return ValidationErrors.validation(result);
		}
		return userService.updateUser(id, userDTO)
                .map(UserMapper::toResponseDTO) 
                .map(u -> ResponseEntity.ok(u))
				.orElse(ResponseEntity.notFound().build());
	}
    
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> delete(@PathVariable Long id) {
		return userService.deleteUser(id)
                .map(UserMapper::toResponseDTO) 
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
	}
}