package com.antonio.projects.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antonio.projects.parking.enums.RoleName;
import com.antonio.projects.parking.models.Rol;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Long> {
	Optional<Rol> findByName(RoleName name);
}
