package com.hitss.AcademyPlatform.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitss.AcademyPlatform.entities.Curso;
@Repository

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
