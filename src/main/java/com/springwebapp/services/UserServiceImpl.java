package com.springwebapp.services;

import java.util.List;

//import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springwebapp.entities.Role;
import com.springwebapp.entities.User;
import com.springwebapp.repository.RoleRepository;
import com.springwebapp.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	
	private final String USER_ROLE="USER";
	private final String ADMIN_ROLE="ADMIN";
	
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}



	@Override
	public User findByUsername(String username) {
		
		return userRepo.findByUsername(username);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user);
	}



	@Override
	public boolean registerUser(User user) {
		
		List<User> userek=userRepo.findAll();
		
		for(User u: userek) {
			if(u.getUsername().equals(user.getUsername()) && u.getEmail().equals(user.getEmail()))
				return false;
		}
		
		Role adminRole=roleRepo.findByRole(ADMIN_ROLE);
		Role userRole=roleRepo.findByRole(USER_ROLE);

		if(userRole!=null) {
			user.getRoles().add(userRole);
		}
		else {
			user.addRoles(USER_ROLE);
		}
		
		if(user.getUsername().equals("admin")) {
			if(adminRole!=null) {
				//user.getRoles().add(userRole);
				user.getRoles().add(adminRole);
			}
			else {
				//user.addRoles(USER_ROLE); nem szabad hozzáadni
				user.addRoles(ADMIN_ROLE);
			}
		}
		userRepo.save(user);
		return true;
	}
	//@PostConstruct
	public void init() {
		userRepo.delete(findByUsername("admin"));
	}

}
