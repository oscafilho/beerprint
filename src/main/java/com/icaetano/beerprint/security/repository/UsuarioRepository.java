package com.icaetano.beerprint.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icaetano.beerprint.security.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByUsername(String username);

    Boolean existsByUsername(String username);
  
    Boolean existsByEmail(String email);
}