package com.andersson.practicase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Facultad;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Integer> {
}