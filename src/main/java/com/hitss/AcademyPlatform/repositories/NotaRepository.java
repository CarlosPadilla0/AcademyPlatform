package com.hitss.AcademyPlatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hitss.AcademyPlatform.entities.Nota;
@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    
    List<Nota> findByEstudianteId(Long estudianteId);

    @Query("SELECT n FROM Nota n JOIN FETCH n.asignatura a JOIN FETCH a.curso")
    List<Nota> findAllWithDetails();
}
