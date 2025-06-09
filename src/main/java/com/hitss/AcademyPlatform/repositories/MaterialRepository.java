package com.hitss.AcademyPlatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitss.AcademyPlatform.entities.Material;
@Repository

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByAsignaturaId(Long asignaturaId);


}
