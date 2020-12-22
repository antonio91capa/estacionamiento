package com.antonio.projects.parking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.projects.parking.enums.RoleName;
import com.antonio.projects.parking.models.Rol;
import com.antonio.projects.parking.repository.RoleRepository;
import com.antonio.projects.parking.service.IRoleService;

@Service
public class RolesServiceImpl implements IRoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RolesServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Optional<Rol> findByName(RoleName name) {
		return roleRepository.findByName(name);
	}

}
