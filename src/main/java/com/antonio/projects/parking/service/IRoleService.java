package com.antonio.projects.parking.service;

import java.util.Optional;

import com.antonio.projects.parking.enums.RoleName;
import com.antonio.projects.parking.models.Rol;

public interface IRoleService {
	Optional<Rol> findByName(RoleName name);
}
