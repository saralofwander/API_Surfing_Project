package com.repositories;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.entities.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
@Service
public class UserInMemoryRepository {
	
	Map<Long, User> users = new HashMap<Long, User>();	
	
	public Collection<User> getAll() {		
		return users.values();
	}
	
	public User getById(Long id) {
		return users.get(id);
	}
	
	public User addUser(User user) {
		users.put(user.getId(), user);
		return users.get(user.getId());
	}
	
	public User updateUser(Long id, User user) {
		users.put(id, user);
		return users.get(id);
	}
	
	public void deleteUser(Long id) {
		users.remove(id);
	}
	
}