package com.andersson.practicase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.OtrasAc;
import com.andersson.practicase.model.PlanTrabajo;

@Repository
public interface OtrasAcRepository extends JpaRepository<OtrasAc, Integer> {
    List<OtrasAc> findByPlanTrabajoIdPlantrabajo(Integer idPlanTrabajo);

    Object findByPlanTrabajo(PlanTrabajo plan);
}