package com.springwebapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springwebapp.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
	User findByUsername(String username);
	
	List<User> findAll();
	
}
