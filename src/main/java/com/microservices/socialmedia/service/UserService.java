package com.microservices.socialmedia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.microservices.socialmedia.model.User;

@Component
public class UserService {
	
	private static ArrayList<User> users=new ArrayList<>();
	static {
	users.add(new User(1,"james",new Date()));
	users.add(new User(2,"john doe",new Date()));
	users.add(new User(3,"tim",new Date()));
	}
	
	public ArrayList<User> getAllUsers() {
		// TODO Auto-generated method stub
		return users;
	}

	public User getById(int id) {
		// TODO Auto-generated method stub
		for(User user:users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		users.add(user);
	}

	public User deleteById(int id) {
		// TODO Auto-generated method stub
		Iterator<User> it= users.iterator();
		while(it.hasNext()) {
			User user=it.next();
			if(user.getId()==id) {
				it.remove();
				return null;
			}
			
			
		}


		return null;
	}
	
	

}
