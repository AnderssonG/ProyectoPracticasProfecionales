package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.AcDocencia;
import com.andersson.practicase.repository.AcDocenciaRepository;

@Service
public class AcDocenciaService {
    @Autowired
    private AcDocenciaRepository repository;

    public List<AcDocencia> listar() {
        return repository.findAll();
    }

    public AcDocencia guardar(AcDocencia actividad) {
        return repository.save(actividad);
    }

    public Optional<AcDocencia> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<AcDocencia> buscarPorPlanTrabajo(Integer idPlan) {
        return repository.findByPlanTrabajoIdPlantrabajo(idPlan);
    }
    public void saveAll (List<AcDocencia> acs){
        for(AcDocencia ax : acs){
            repository.save(ax);
        }
    }
}
