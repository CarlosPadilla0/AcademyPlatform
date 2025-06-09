package com.hitss.AcademyPlatform.services.Impls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.estudiante.EstudianteCreateRequestDTO;
import com.hitss.AcademyPlatform.entities.Estudiante;
import com.hitss.AcademyPlatform.entities.Nota;
import com.hitss.AcademyPlatform.entities.Role;
import com.hitss.AcademyPlatform.repositories.EstudianteRepository;
import com.hitss.AcademyPlatform.repositories.RoleRepository;
import com.hitss.AcademyPlatform.services.service.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	@Autowired
	private EstudianteRepository estudianteRepository;
	@Autowired
	private RoleRepository roleRepository; 

	@Autowired
	private PasswordEncoder passwordEncoder; 


	@Override
	@Transactional(readOnly = true)
	public List<Estudiante> findAllEstudiantes() {
		return estudianteRepository.findAll();
	}

	@Override
	@Transactional
	public Estudiante saveEstudiante(EstudianteCreateRequestDTO estudianteDTO) {
        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() -> new RuntimeException("Error: Rol STUDENT no encontrado."));

        Estudiante nuevoEstudiante = new Estudiante();
        
        nuevoEstudiante.setNombre(estudianteDTO.getNombre());
        nuevoEstudiante.setEmail(estudianteDTO.getEmail());
        nuevoEstudiante.setPassword(passwordEncoder.encode(estudianteDTO.getPassword())); 
        nuevoEstudiante.setRole(studentRole);
        nuevoEstudiante.setCodigoMatricula(estudianteDTO.getCodigoMatricula());
		return estudianteRepository.save(nuevoEstudiante);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Estudiante> getEstudianteById(Long id) {
		return estudianteRepository.findById(id);
	}

	@Override
	public List<Nota> getNotasByEstudianteId(Long id) {
		return estudianteRepository.findById(id)
			.map(Estudiante::getNotas)
			.orElse(List.of());
	}

	public Optional<Estudiante> findByUsuarioId(Long usuarioId) {
		return estudianteRepository.findAll().stream()
			.filter(e -> usuarioId.equals(e.getId()))
			.findFirst();
	}

}
