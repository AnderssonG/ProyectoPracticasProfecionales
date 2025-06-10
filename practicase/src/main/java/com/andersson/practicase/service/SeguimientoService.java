package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersson.practicase.model.Seguimiento;
import com.andersson.practicase.repository.SeguimientoRepository;

@Service
public class SeguimientoService {
    
    private SeguimientoRepository repository;

    

    public SeguimientoService(SeguimientoRepository repository) {
        this.repository = repository;
    }

    public List<Seguimiento> listar() {
        return repository.findAll();
    }

    public Seguimiento guardar(Seguimiento seguimiento) {
        return repository.save(seguimiento);
    }

    public Optional<Seguimiento> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<Seguimiento> buscarPorPlanTrabajo(Integer idPlan) {
        return repository.findByPlanTrabajoIdPlantrabajo(idPlan);
    }

    public void saveAll (List<Seguimiento> acs){
        for(Seguimiento ax : acs){
            repository.save(ax);
        }
    }
}

