package com.springwebapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springwebapp.services.NumberService;

@Controller
public class IndexPageController {

	private NumberService numberService;


	@Autowired
	public void setNumberService(NumberService numberService) {
		this.numberService = numberService;
	}
	

	
	@RequestMapping({"/index","/"})
	public String home(Model model){
		List<List<Integer>> num1=numberService.getNumbers1();
		List<List<Integer>> num2=numberService.getNumbers3();
		
		model.addAttribute("numbersList1", num1);
		model.addAttribute("numbersList2", num2);
		
		return "index";
	}
}
