package com.springwebapp.services;

import com.springwebapp.entities.User;

public interface UserService {

	User findByUsername (String username);
	
	boolean registerUser (User user);
}
