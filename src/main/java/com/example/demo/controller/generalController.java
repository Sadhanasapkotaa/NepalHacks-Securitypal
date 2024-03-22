package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.User;
import com.example.demo.repository.userRepository;


@Controller
public class generalController {

	
	@Autowired
	private userRepository uRepo;
	
	@GetMapping("/appointment")
	public String appointment() {
		return "appointmentform.html";
	}
	
	@GetMapping("/landingpage")
	
	public String landingpage(@ModelAttribute User u, Model model) {
		
		model.addAttribute("user", u.getFullname());
		
		if(uRepo.existsByEmailAndPassword(u.getEmail(), u.getPassword())) {
			List<User> userList = uRepo.findAll();
			model.addAttribute("userList", userList);
			
			
			return "landingpage.html";
		}
		
		
		return "landingpage.html";
	}
	
	@GetMapping("/userlanding")
	public String userLanding() {
		return "userLanding.html";
	}
}
