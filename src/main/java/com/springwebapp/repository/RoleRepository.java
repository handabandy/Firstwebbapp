package com.springwebapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.springwebapp.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByRole(String role);
}
