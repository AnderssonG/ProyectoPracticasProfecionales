package com.andersson.practicase.repository;

import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersson.practicase.model.Docente;
import com.andersson.practicase.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUserName(String userName);
    Optional<Usuario> findByDocente(Docente docente);
    List<Usuario> findAllByOrderByDocente_NombresAsc();

    
}