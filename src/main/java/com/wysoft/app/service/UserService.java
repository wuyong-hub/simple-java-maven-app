package com.wysoft.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.wysoft.app.model.User;

@Service
public class UserService {
	public User add(User user) {
		String id = UUID.randomUUID().toString();
		user.setId(id);
		return user;
	}
	public User get(String id) {
		User user = new User();
		user.setId(id);
		user.setUsername("Kevin");
		user.setName("张三");
		return user;
	}
	
	public List<User> get(){
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername("Kevin");
		user.setName("张三");
		
		User user1 = new User();
		user1.setId(UUID.randomUUID().toString());
		user1.setUsername("Tom");
		user1.setName("李二");
		
		users.add(user);
		users.add(user1);
		
		return users;
	}
	
	public void remove(String id) {
		
	}
	
	public void update(User user) {
		
	}
	
}
