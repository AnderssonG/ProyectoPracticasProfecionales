package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.Extension;
import com.andersson.practicase.repository.ExtensionRepository;

@Service
public class ExtensionService {
    @Autowired
    private ExtensionRepository repository;

    public List<Extension> listar() {
        return repository.findAll();
    }

    public Extension guardar(Extension actividad) {
        return repository.save(actividad);
    }

    public Optional<Extension> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<Extension> buscarPorPlanTrabajo(Integer idPlan) {
        return repository.findByPlanTrabajoIdPlantrabajo(idPlan);
    }
    public void saveAll (List<Extension> acs){
        for(Extension ax : acs){
            repository.save(ax);
        }
    }
}
