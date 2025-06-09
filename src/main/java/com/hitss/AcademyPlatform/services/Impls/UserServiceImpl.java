package com.hitss.AcademyPlatform.services.Impls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.usuario.UsuarioUpdateRequestDTO;
import com.hitss.AcademyPlatform.entities.Usuario;
import com.hitss.AcademyPlatform.repositories.UserRepository;
import com.hitss.AcademyPlatform.services.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	 @Autowired
	 private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllUsers() {
		return userRepository.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> getUserById(Long id) {
		return userRepository.findById(id); 
		}

	@Override
	@Transactional
	public Optional<Usuario> updateUser(Long id, UsuarioUpdateRequestDTO userDTO) {
        return userRepository.findById(id).map(userDB -> {
            userDB.setNombre(userDTO.getNombre());
            userDB.setEmail(userDTO.getEmail());

            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                userDB.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            return userRepository.save(userDB);
        });
    }
	@Override
	@Transactional
	public Optional<Usuario> deleteUser(Long id) {
		Optional<Usuario> optionalUser = userRepository.findById(id);
		optionalUser.ifPresent(userRepository::delete);
		return optionalUser;
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {
		return userRepository.save(user); }
}
