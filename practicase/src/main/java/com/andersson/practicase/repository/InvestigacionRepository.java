package com.andersson.practicase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Investigacion;
import com.andersson.practicase.model.PlanTrabajo;

@Repository
public interface InvestigacionRepository extends JpaRepository<Investigacion, Integer> {
    List<Investigacion> findByPlanTrabajoIdPlantrabajo(Integer idPlanTrabajo);

    Object findByPlanTrabajo(PlanTrabajo plan);
}