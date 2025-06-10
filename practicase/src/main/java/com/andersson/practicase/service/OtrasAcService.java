package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.OtrasAc;
import com.andersson.practicase.repository.OtrasAcRepository;

@Service
public class OtrasAcService {
    @Autowired
    private OtrasAcRepository repository;

    public List<OtrasAc> listar() {
        return repository.findAll();
    }

    public OtrasAc guardar(OtrasAc actividad) {
        return repository.save(actividad);
    }

    public Optional<OtrasAc> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<OtrasAc> buscarPorPlanTrabajo(Integer idPlan) {
        return repository.findByPlanTrabajoIdPlantrabajo(idPlan);
    }
    public void saveAll (List<OtrasAc> acs){
        for(OtrasAc ax : acs){
            repository.save(ax);
        }
    }
}
