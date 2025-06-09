package com.hitss.AcademyPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitss.AcademyPlatform.entities.PeriodoLectivo;
@Repository

public interface PeriodoRepository extends JpaRepository<PeriodoLectivo, Long> {

}
