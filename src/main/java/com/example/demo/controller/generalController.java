package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class generalController {

	
	@GetMapping("/appointment")
	public String appointment() {
		return "appointmentform.html";
	}
	
	@GetMapping("/landingpage")
	
	public String landingpage() {
		return "landingpage.html";
	}
}
