package com.andersson.practicase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Docente;
import com.andersson.practicase.model.PlanTrabajo;

@Repository
public interface PlanTrabajoRepository extends JpaRepository<PlanTrabajo, Integer> {
    List<PlanTrabajo> findByDocenteId(Integer id);
    List<PlanTrabajo> findBySemestreId(Integer idSemestre);
    Object findByDocente(Docente docente);
    PlanTrabajo findTopByDocenteOrderBySemestreFechaFinDesc(Docente docente);
}