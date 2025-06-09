package com.hitss.AcademyPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitss.AcademyPlatform.entities.Matricula;
@Repository

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    boolean existsByEstudianteIdAndCursoIdAndPeriodoLectivoId(Long estudianteId, Long cursoId, Long periodoId);


}
