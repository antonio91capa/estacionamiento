package com.antonio.projects.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antonio.projects.parking.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
	Optional<Usuario> findByUsername(String username);
}
