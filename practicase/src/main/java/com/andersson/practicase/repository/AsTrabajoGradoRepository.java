package com.andersson.practicase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.AsTrabajoGrado;
import com.andersson.practicase.model.PlanTrabajo;

@Repository
public interface AsTrabajoGradoRepository extends JpaRepository<AsTrabajoGrado, Integer> {
    List<AsTrabajoGrado> findByPlanTrabajoIdPlantrabajo(Integer idPlanTrabajo);

    Object findByPlanTrabajo(PlanTrabajo plan);
}