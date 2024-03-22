package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.EventDetails;

public interface eventRepository extends JpaRepository<EventDetails, Integer>{
	
	

}
