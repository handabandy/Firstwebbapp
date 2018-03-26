package com.springwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springwebapp.component.Database;
import com.springwebapp.services.JokeService;

@Controller
public class JokeController {
	
	private JokeService jokeService;
	private Database database;

	
	
	public JokeController(JokeService jokeService, Database database) {
		super();
		this.jokeService = jokeService;
		this.database = database;
	}



	@RequestMapping("/joke")
	public String showJoke(Model model) {
		
		//model.addAttribute("pageTitle","Szeva Te Kis Kötsög!");
		model.addAttribute("joke",jokeService.getJoke());
		model.addAttribute("username",database.getUserName());
		model.addAttribute("password", database.getPassword());
		model.addAttribute("url", database.getUrl());
		return "chucknorris";
	}

}
