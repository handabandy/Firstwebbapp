package com.springwebapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springwebapp.entities.User;
import com.springwebapp.services.BloggerService;
import com.springwebapp.services.EmailService;
import com.springwebapp.services.StoryService;
import com.springwebapp.services.UserService;

@Controller

public class StoriesController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private StoryService storyService;
	private EmailService emailService;
	private BloggerService bloggerService;
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setBloggerService(BloggerService bloggerService) {
		this.bloggerService = bloggerService;
	}

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
}
	

	public StoriesController(StoryService storyService) {
		super();
		this.storyService = storyService;
	}
	
	
	
	//@Secured("ROLE_USER") nem szükséges, ha van a SecureConfig-ban configure() metódus.
	@RequestMapping("/stories")
	public String showStories(Model model)
	{
		//model.addAttribute("pageTitle","Szeva Te Kis Kötsög!");
		model.addAttribute("napiSzar","napiSZAR.com");
		model.addAttribute("stories",storyService.getStories());
		
		return "stories";
	}
	
	
	//@Secured("ROLE_ADMIN") nem szükséges, ha van a SecureConfig-ban configure() metódus.
	@RequestMapping("/story")
	public String story(Model model) {
		//model.addAttribute("pageTitle", "Szeva Te Kis Nyomi!");
		model.addAttribute("napiSzar","napiSZAR.com");
		model.addAttribute("story", storyService.getStory());
		return "story";
	}
	
	@RequestMapping({"/index","/"})
	public String home(){
		return "index";
	}
	
	@RequestMapping("/bloggers")
	public String bloggers(Model model){
		//model.addAttribute("pageTitle", "Szeva Te Kis Nyomi!");
		model.addAttribute("bloggers",bloggerService.getBloggers() );
		return "bloggers";
	}
	
	@RequestMapping("/sztorik")
	public String stories(Model model){
		//model.addAttribute("pageTitle","Szeva Te Kis Kötsög!");
		model.addAttribute("stories",storyService.getStories());
		return "sztorik";
	}
	
	@RequestMapping("title/{title}")
	public String searchTitle(@PathVariable(value="title") String title, Model model)
	{
		if (title=="") {
			//model.addAttribute("pageTitle", "Szeva Te Kis Nyomi!");
			model.addAttribute("napiSzar","napiSZAR.com");
			model.addAttribute("story", storyService.getSpecialStory());
			return "story";
		}
			//model.addAttribute("pageTitle", "Szeva Te Kis Nyomi!");
			model.addAttribute("napiSzar","napiSZAR.com");
			model.addAttribute("story", storyService.getSpecifiedStory(title));
			return "story";
		
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
//	@RequestMapping(value = "/reg", method = RequestMethod.POST) ez ugyanazt csinálja, mint a következő sor.
	@PostMapping("/reg")
    public String greetingSubmit(@ModelAttribute User user) {
		log.info("Uj user!");
		emailService.sendMessage(user.getEmail(),user.getFirstname());
		log.info(user.getUsername());
		log.debug(user.getPassword());
		boolean valasz=userService.registerUser(user);
		if (!valasz) {
			return"unsuccess";
		}
		
        return "auth/login";
}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest rA, Exception ex, Model model) {
		model.addAttribute("errMessage", ex.getMessage());
		return "exceptionHandler";
	}

}
