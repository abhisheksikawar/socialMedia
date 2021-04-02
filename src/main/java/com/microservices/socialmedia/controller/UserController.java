package com.microservices.socialmedia.controller;

import java.net.URI;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservices.socialmedia.exception.NoContentException;
import com.microservices.socialmedia.exception.UserException;
import com.microservices.socialmedia.model.User;
import com.microservices.socialmedia.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path="/users")
	public ArrayList<User> getAllUser() {
		ArrayList<User> allUser=userService.getAllUsers();
		if(allUser==null||allUser.size()==0) {
			throw new NoContentException("No data available");
		}
		return allUser;
		
	}
	@GetMapping(path="/users/{id}")
	public User getUserById(@PathVariable int id ) {
		User user= userService.getById(id);
		if(user==null) {
			throw new UserException("Id-: "+id);
			
		}
		return user;
	}
	
	@PostMapping(path="/saveUser")
		public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
		
		userService.saveUser(user);
		
		URI location =ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
			
		}
	
	@DeleteMapping(path="/user/{id}")
	public void deleteUserById(@PathVariable int id) {
		
		User user=userService.deleteById(id);
		if(user==null) {
			throw new UserException("No User available for id "+id);
		}
	}
	

}
