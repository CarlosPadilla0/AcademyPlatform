package com.hitss.AcademyPlatform.services.Impls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.AcademyPlatform.dto.profesor.ProfesorCreateRequestDTO;
import com.hitss.AcademyPlatform.entities.Asignatura;
import com.hitss.AcademyPlatform.entities.Profesor;
import com.hitss.AcademyPlatform.entities.Role;
import com.hitss.AcademyPlatform.repositories.AsignaturaRepository;
import com.hitss.AcademyPlatform.repositories.ProfesorRepository;
import com.hitss.AcademyPlatform.repositories.RoleRepository;
import com.hitss.AcademyPlatform.services.service.ProfesorService;

@Service
public class ProfesorServiceImpl implements ProfesorService {

	@Autowired
	private ProfesorRepository profesorRepository;
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<Profesor> getAllProfesores() {
		return profesorRepository.findAll();
	}

	@Override
	@Transactional
	public Profesor save(ProfesorCreateRequestDTO profesorDTO) {
        Role teacherRole = roleRepository.findByName("TEACHER")
                .orElseThrow(() -> new RuntimeException("Error: Rol TEACHER no encontrado."));
        Profesor nuevoProfesor = new Profesor();
        nuevoProfesor.setNombre(profesorDTO.getNombre());
        nuevoProfesor.setEmail(profesorDTO.getEmail());
        nuevoProfesor.setPassword(passwordEncoder.encode(profesorDTO.getPassword()));
        nuevoProfesor.setRole(teacherRole);

        nuevoProfesor.setEspecialidad(profesorDTO.getEspecialidad());

		return profesorRepository.save(nuevoProfesor);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Profesor> getProfesorById(Long id) {
		return profesorRepository.findById(id);
	}

	@Override
	public List<Asignatura> getAsignaturasAsignadas(Long profesorId) {
		return asignaturaRepository.findByProfesorId(profesorId);
	}

}
