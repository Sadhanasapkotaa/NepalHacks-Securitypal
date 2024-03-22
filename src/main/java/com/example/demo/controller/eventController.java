package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.EventDetails;
import com.example.demo.repository.eventRepository;

@Controller
public class eventController {
	
	@Autowired
	private eventRepository eventRepo;
	

	
	@PostMapping("/postEvent")
	public String postEvent(@RequestParam("img") MultipartFile img,
	                        @ModelAttribute EventDetails evd,
	                        Model model) {
	    try {
	    
	        EventDetails im = new EventDetails();
	        im.setEventname(evd.getEventname());
	        im.setDate(evd.getDate());
	        im.setTime(evd.getTime());
	        im.setLocation(evd.getLocation());
	        im.setDescription(evd.getDescription());
	        im.setFeeStatus("Free");

	        im.setImage(img.getOriginalFilename());

	
	        EventDetails savedEvent = eventRepo.save(im);

	      
	        if (savedEvent != null) {
	            try {
	
	                File saveDir = new ClassPathResource("static/img").getFile();
	                Path imagePath = Paths.get(saveDir.getAbsolutePath() + File.separator + img.getOriginalFilename());
	                Files.copy(img.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
	                
	                System.out.println(imagePath);

	            
	            	List<EventDetails> eventList = eventRepo.findAll();
	        		model.addAttribute("eventList", eventList);
	                return "event.html";
	                
	                
	            } catch (Exception e) {
	             
	                e.printStackTrace();
	                model.addAttribute("error", "Failed to upload image");
	                return "eventform.html";
	            }
	        }
	    } catch (Exception e) {
	   
	        e.printStackTrace();
	        model.addAttribute("error", "Failed to save event details");
	    }

	  
	    return "eventdform.html";
	}
	
	
	
	
	@GetMapping("/event")
	
	public String getEventsDetails(Model model){
		List<EventDetails> eventList = eventRepo.findAll();
		model.addAttribute("eventList", eventList);
		
		return "event.html";
	}
	
	@GetMapping("/addEvents")
	
	public String addEvebts() {
		
		return "eventform.html";
		
	}
 	

}
