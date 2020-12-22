package com.antonio.projects.parking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.projects.parking.models.Usuario;
import com.antonio.projects.parking.repository.UsuarioRepository;
import com.antonio.projects.parking.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public boolean existsByUsername(String username) {
		return usuarioRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	@Override
	public Optional<Usuario> findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

}
