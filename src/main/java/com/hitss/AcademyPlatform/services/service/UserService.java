package com.hitss.AcademyPlatform.services.service;

import java.util.List;
import java.util.Optional;

import com.hitss.AcademyPlatform.dto.usuario.UsuarioUpdateRequestDTO;
import com.hitss.AcademyPlatform.entities.Usuario;

public interface UserService {
	List<Usuario> findAllUsers();
	Usuario save(Usuario user);
	Optional<Usuario> getUserById(Long id);
	Optional<Usuario> updateUser(Long id, UsuarioUpdateRequestDTO userDTO); 
	Optional<Usuario> deleteUser(Long id);
}
