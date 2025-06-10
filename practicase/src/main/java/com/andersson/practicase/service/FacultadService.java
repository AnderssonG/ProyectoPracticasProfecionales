package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.Facultad;
import com.andersson.practicase.repository.FacultadRepository;

@Service
public class FacultadService {
    @Autowired
    private FacultadRepository repository;

    public List<Facultad> listar() {
        return repository.findAll();
    }

    public Facultad guardar(Facultad facultad) {
        return repository.save(facultad);
    }

    public Optional<Facultad> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}

