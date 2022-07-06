package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	///GetMapping = Get INfo
	///PostMapping = Add or change
	//Curly braces = variable -> {id} from "site"
	//To get a variable from address bar, use @PathVariable
	@GetMapping("/user")
	public List<User> getUsers() {
		return repo.findAll();
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable String id) throws ResourceNotFoundException{
		
		Optional<User> found = repo.findById(id);
		
		if(found.isPresent()) {
			return found.get();
		}
		
		throw new ResourceNotFoundException("User with id = " + id + " not found");
	}
	
	///Use JSON to input a class to a table object (Body)
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User created = repo.insert(user);
		
		return ResponseEntity.status(201).body(created);
	}
	
	
	
	
}











