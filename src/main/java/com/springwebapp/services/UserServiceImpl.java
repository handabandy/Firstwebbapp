package com.springwebapp.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.springwebapp.services.EmailService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BloggerRepository bloggerrepo;
	private EmailService emailService;
	
	private final String USER_ROLE="USER";
	private final String ADMIN_ROLE="ADMIN";
	
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, BloggerRepository bRepository) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.bloggerrepo=bRepository;
	}

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
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
	public String registerUser(User user) {
		
		String result=controllUser(user);
		List<User> userek;
		
		if(result.equals("ok")) {
			try {
		         userek=userRepo.findAll();
			}
			catch (Exception e) {
				return "Az adatbázis kapcsolat megszakadt. Próbáld meg késöbb ismét!";
			}
			
		
		for(User u: userek) {
			if (u==null) {
				return "Hiba az adatbázis-kapcsolatban. Próbálkozz késöbb!";
			}
			if(u.getUsername().equals(user.getUsername()) && u.getEmail().equals(user.getEmail()))
				return "A felhasználónév vagy az e-mail cím már foglalt!!!";
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
		String activationKey=generateKey();
		emailService.sendMessage(user.getEmail(), user.getFirstname(), activationKey);
		user.setNotrobot(false);
		user.setActivation(activationKey);
		try {
		userRepo.save(user);
		}
		catch (Exception e) {
			return "A mentés nem sikerült!";
		}
		
		return "ok";
		}
		else
			return result;
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
		
		User user;
		try {
			user=userRepo.findByActivation(code);
		} catch (Exception e) {
			return "Hiba az adatbáziskapcsolatban!";
		}
		
		if(user==null) {
			return "Hiba, a felhasználó nem található!";
		}
		user.setNotrobot(true);
		user.setActivation("");
		String name=user.getLastname()+" "+user.getFirstname();
		Blogger blogger=new Blogger(name, user.getUsername());
		try {
		bloggerrepo.save(blogger);
		userRepo.save(user);
		}
		catch (Exception e) {
			return "A mentés nem sikerált!!!";
		}
		return "ok";
	}

	public String controllUser(User user) {
		if (user.getEmail().equals("")) {
			return "Az e-mail nem lehet üres!";
		}
		if (!(user.getEmail().equalsIgnoreCase("bonta@mailbox.hu"))) {
			return "Az e-mail cím nem megfelelö";
		}
		if (user.getFirstname().equals("")) {
			return "A Keresztnév nem lehet üres!";
		}
		if (user.getLastname().equals("")) {
			return "A Vezetéknév nem lehet üres!";
		}
		if (user.getPassword().equals("")) {
			return "A Jelszó nem lehet üres!";
		}
		if (user.getUsername().equals("")) {
			return "A Felhasználónév nem lehet üres!";
		}
		return "ok";
	}
}
