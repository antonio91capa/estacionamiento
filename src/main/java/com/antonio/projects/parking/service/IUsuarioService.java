package com.antonio.projects.parking.service;

import java.util.Optional;

import com.antonio.projects.parking.models.Usuario;

public interface IUsuarioService {
	Usuario save(Usuario usuario);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	Optional<Usuario> findByUsername(String username);
}
