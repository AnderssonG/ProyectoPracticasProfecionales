package com.andersson.practicase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.AcDocencia;
import com.andersson.practicase.model.PlanTrabajo;

@Repository
public interface AcDocenciaRepository extends JpaRepository<AcDocencia, Integer> {
    List<AcDocencia> findByPlanTrabajoIdPlantrabajo(Integer idPlanTrabajo);

    Object findByPlanTrabajo(PlanTrabajo plan);
}