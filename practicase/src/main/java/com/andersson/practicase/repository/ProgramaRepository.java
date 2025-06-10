package com.andersson.practicase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Programa;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer> {

    Optional<Programa> findBySiglas(String siglas);
}