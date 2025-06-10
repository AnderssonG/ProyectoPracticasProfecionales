package com.andersson.practicase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Extension;
import com.andersson.practicase.model.PlanTrabajo;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Integer> {
    List<Extension> findByPlanTrabajoIdPlantrabajo(Integer idPlanTrabajo);

    Object findByPlanTrabajo(PlanTrabajo plan);
}