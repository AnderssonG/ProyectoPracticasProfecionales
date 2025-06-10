package com.andersson.practicase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Semestre;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Integer> {}