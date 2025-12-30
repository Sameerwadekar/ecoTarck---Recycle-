package com.learn.ecostrack.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learn.ecostrack.entities.Role;
import java.util.Optional;

import com.learn.ecostrack.enums.AppRole;

@RepositoryRestResource(path = "role")
public interface RoleRepositary extends JpaRepository<Role, Integer> {
	Optional<Role> findByAppRole(AppRole appRole);
}
