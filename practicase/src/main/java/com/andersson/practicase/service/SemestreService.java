package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.Semestre;
import com.andersson.practicase.repository.SemestreRepository;

@Service
public class SemestreService {
    @Autowired
    private SemestreRepository repository;

    public List<Semestre> listar() {
        return repository.findAll();
    }

    public Semestre guardar(Semestre semestre) {
        return repository.save(semestre);
    }

    public Optional<Semestre> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}

