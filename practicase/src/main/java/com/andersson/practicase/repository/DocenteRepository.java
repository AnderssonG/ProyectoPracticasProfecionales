package com.andersson.practicase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {}