package com.andersson.practicase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.PlanTrabajo;
import com.andersson.practicase.model.Seguimiento;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
    List<Seguimiento> findByPlanTrabajoIdPlantrabajo(Integer idPlanTrabajo);

    Object findByPlanTrabajo(PlanTrabajo plan);
}