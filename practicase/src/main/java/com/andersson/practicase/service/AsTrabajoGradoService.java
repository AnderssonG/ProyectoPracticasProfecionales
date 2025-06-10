package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.AsTrabajoGrado;
import com.andersson.practicase.repository.AsTrabajoGradoRepository;

@Service
public class AsTrabajoGradoService {
    @Autowired
    private AsTrabajoGradoRepository repository;

    public List<AsTrabajoGrado> listar() {
        return repository.findAll();
    }

    public AsTrabajoGrado guardar(AsTrabajoGrado actividad) {
        return repository.save(actividad);
    }

    public Optional<AsTrabajoGrado> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<AsTrabajoGrado> buscarPorPlanTrabajo(Integer idPlan) {
        return repository.findByPlanTrabajoIdPlantrabajo(idPlan);
    }
    public void saveAll (List<AsTrabajoGrado> acs){
        for(AsTrabajoGrado ax : acs){
            repository.save(ax);
        }
    }
}

