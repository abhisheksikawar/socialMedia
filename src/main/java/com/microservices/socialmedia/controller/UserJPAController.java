package com.microservices.socialmedia.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservices.socialmedia.exception.NoContentException;
import com.microservices.socialmedia.exception.UserException;
import com.microservices.socialmedia.model.Post;
import com.microservices.socialmedia.model.User;
import com.microservices.socialmedia.repository.PostRepository;
import com.microservices.socialmedia.repository.UserRepository;
import com.microservices.socialmedia.service.UserService;

@RestController
@RequestMapping("/jpa")
public class UserJPAController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping(path="/users")
	public List<User> getAllUser() {
		List<User> allUser= userRepository.findAll();
		if(allUser==null||allUser.size()==0) {
			throw new NoContentException("No data available");
		}
		return allUser;
		
	}
	@GetMapping(path="/users/{id}")
	public Optional<User> getUserById(@PathVariable int id ) {
		Optional<User> user= userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserException("Id-: "+id);
			
		}
		return user;
	}
	
	@PostMapping(path="/saveuser")
		public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
		
		userRepository.save(user);
		
		URI location =ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
			
		}
	
	@DeleteMapping(path="/user/{id}")
	public void deleteUserById(@PathVariable int id) {
		
		userRepository.deleteById(id);
		
	}
	
	
	@GetMapping(path="/users/{id}/posts")
	public List<Post> getPostsById(@PathVariable int id ) {
		Optional<User> user= userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserException("Id-: "+id);
			
		}
		List<Post> post=user.get().getPost();
		return post;
	}
	
	@PostMapping(path="/users/{id}/posts/create")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post ) {
		
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserException("Id "+id);
		}
		
		post.setUser(user.get());
		
		postRepository.save(post);
		
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("postId").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(path="/users/{id}/posts/{postId}")
		public Post getPostById(@PathVariable int id,@PathVariable int postId) {
		
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserException("id :"+id);
		}
		Optional<Post>post=postRepository.findById(postId);
			return post.get();
			
		}
	

	

}
