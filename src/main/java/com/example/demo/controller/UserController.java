package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@PostMapping("/update")
	public User updateUser(@RequestBody User userDetails) {
	    return userRepository.findById(userDetails.getId())
	            .map(user -> {
	                user.setEmail(userDetails.getEmail());
	                user.setFirstName(userDetails.getFirstName());
	                user.setLastName(userDetails.getLastName());
	                return userRepository.save(user);
	            })
	            .orElseThrow(() -> new RuntimeException("User not found with id " + userDetails.getId()));
	}
}