package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.Programa;
import com.andersson.practicase.repository.ProgramaRepository;

@Service
public class ProgramaService {
    @Autowired
    private ProgramaRepository repository;

    public List<Programa> listar() {
        return repository.findAll();
    }

    public Programa guardar(Programa programa) {
        return repository.save(programa);
    }

    public Optional<Programa> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
    public Optional<Programa> buscarPorSiglas(String siglas) {
        return repository.findBySiglas(siglas);
    }
}
