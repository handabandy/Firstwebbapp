package com.springwebapp.services;

import com.springwebapp.entities.User;

public interface UserService {

	User findByUsername (String username);
	
	String registerUser (User user);
	
	String userActivation(String code);
}
