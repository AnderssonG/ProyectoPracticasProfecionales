package com.andersson.practicase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersson.practicase.model.Docente;
import com.andersson.practicase.repository.DocenteRepository;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository repository;

    public List<Docente> listar() {
        return repository.findAll();
    }

    public Docente guardar(Docente docente) {
        return repository.save(docente);
    }

    public Optional<Docente> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
