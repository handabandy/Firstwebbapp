package com.springwebapp.services;

import java.util.List;
import java.util.Random;



//import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springwebapp.component.Blogger;
import com.springwebapp.entities.Role;
import com.springwebapp.entities.User;
import com.springwebapp.repository.BloggerRepository;
import com.springwebapp.repository.RoleRepository;
import com.springwebapp.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BloggerRepository bloggerrepo;
	
	private final String USER_ROLE="USER";
	private final String ADMIN_ROLE="ADMIN";
	
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, BloggerRepository bRepository) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.bloggerrepo=bRepository;
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
		user.setNotrobot(false);
		user.setActivation(generateKey());
		userRepo.save(user);
		String name=user.getLastname()+" "+user.getFirstname();
		Blogger blogger=new Blogger(name, user.getUsername());
		bloggerrepo.save(blogger);
		return true;
	}
	
	private String generateKey() {
		Random random=new Random();
		char[] word=new char[16];
		
		for(int i=0; i<word.length; i++) {
			word[i]=(char)('a'+random.nextInt(26));
		}
		return new String(word);
	}



	//@PostConstruct
	public void init() {
		//userRepo.delete(findByUsername("admin"));
	}



	@Override
	public String userActivation(String code) {
		User user=userRepo.findByActivation(code);
		if(user==null) {
			return "hiba";
		}
		user.setNotrobot(true);
		user.setActivation("");
		userRepo.save(user);
		return "ok";
	}

}
