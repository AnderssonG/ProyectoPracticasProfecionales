package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.Investigacion;
import com.andersson.practicase.repository.InvestigacionRepository;

@Service
public class InvestigacionService {
    @Autowired
    private InvestigacionRepository repository;

    public List<Investigacion> listar() {
        return repository.findAll();
    }

    public Investigacion guardar(Investigacion actividad) {
        return repository.save(actividad);
    }

    public Optional<Investigacion> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<Investigacion> buscarPorPlanTrabajo(Integer idPlan) {
        return repository.findByPlanTrabajoIdPlantrabajo(idPlan);
    }
    public void saveAll (List<Investigacion> acs){
        for(Investigacion ax : acs){
            repository.save(ax);
        }
    }
}

