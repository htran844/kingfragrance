package com.duan.kingfragrance.service;

import com.duan.kingfragrance.model.User;

public interface UserService {
	
	public String findUserAndcreateJWT(String username, String password);
	
	public User createUser(User user);
	
	public Boolean verifyUser(String token); 
}
