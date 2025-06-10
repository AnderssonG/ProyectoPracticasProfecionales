package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.Academica;
import com.andersson.practicase.repository.AcademicaRepository;

@Service
public class AcademicaService {
    @Autowired
    private AcademicaRepository repository;

    public List<Academica> listar() {
        return repository.findAll();
    }

    public Academica guardar(Academica actividad) {
        return repository.save(actividad);
    }

    public Optional<Academica> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<Academica> buscarPorPlanTrabajo(Integer idPlan) {
        return repository.findByPlanTrabajoIdPlantrabajo(idPlan);
    }

    public void saveAll (List<Academica> acs){
        for(Academica ax : acs){
            repository.save(ax);
        }
    }
}

