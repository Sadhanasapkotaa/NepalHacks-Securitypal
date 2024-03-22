package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.repository.userRepository;

@Controller
public class signupController {
	
	@Autowired
	private userRepository uRepo;
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}

	
	@PostMapping("/signupForm")
	public String signupForm(@ModelAttribute User u) {
		uRepo.save(u);
		return "login.html";
	}
	
	@PostMapping("/loginForm")
	public String postLogin(@ModelAttribute User u, Model model) {
		if(uRepo.existsByEmailAndPassword(u.getEmail(), u.getPassword())) {
			List<User> userList = uRepo.findAll();
			model.addAttribute("userList", userList);
			return "homepage.html";
		}
		return "login.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup.html";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
