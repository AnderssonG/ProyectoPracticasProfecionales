package com.andersson.practicase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Academica;
import com.andersson.practicase.model.PlanTrabajo;

@Repository
public interface AcademicaRepository extends JpaRepository<Academica, Integer> {
    List<Academica> findByPlanTrabajoIdPlantrabajo(Integer idPlanTrabajo);

    Object findByPlanTrabajo(PlanTrabajo plan);
}