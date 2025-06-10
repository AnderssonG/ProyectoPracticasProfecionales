package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.PlanTrabajo;
import com.andersson.practicase.repository.PlanTrabajoRepository;
import com.andersson.practicase.repository.SemestreRepository;

@Service
public class PlanTrabajoService {
    @Autowired
    private PlanTrabajoRepository repository;
    @Autowired
    private SemestreRepository semestreRepository;

    public List<PlanTrabajo> listar() {
        return repository.findAll();
    }

    public PlanTrabajo guardar(PlanTrabajo planTrabajo) {
        semestreRepository.save(planTrabajo.getSemestre());
        return repository.save(planTrabajo);
    }

    public Optional<PlanTrabajo> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<PlanTrabajo> buscarPorDocente(Integer id) {
        return repository.findByDocenteId(id);
    }

    public List<PlanTrabajo> buscarPorSemestre(Integer idSemestre) {
        return repository.findBySemestreId(idSemestre);
    }
}
