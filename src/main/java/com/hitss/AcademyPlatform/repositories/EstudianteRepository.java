package com.hitss.AcademyPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitss.AcademyPlatform.entities.Estudiante;
@Repository

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}
