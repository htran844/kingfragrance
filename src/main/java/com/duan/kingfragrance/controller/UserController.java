package com.duan.kingfragrance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duan.kingfragrance.model.User;
import com.duan.kingfragrance.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/admin/getlogin")
	public ResponseEntity<?> getLogin(@RequestBody User user){
		String jwt = userService.findUserAndcreateJWT(user.getUsername(), user.getPassword());
		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}
	
	@PostMapping("/admin/createuser")
	public ResponseEntity<?> createUser(@RequestBody User user){
		User u = userService.createUser(user);
		if (u != null) {
			return new ResponseEntity<>("tạo thành công", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
	}
}
