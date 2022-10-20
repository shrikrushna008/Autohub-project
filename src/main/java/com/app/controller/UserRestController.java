package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequest;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestController {

	public UserRestController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@Autowired
	private IUserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
		System.out.println("In Authenticate User " + request);
		// API : o.s.http.ResponseEntity<T> (T body,HttpStatus stsCode)
		return new ResponseEntity<>(userService.authenticateUser(request.getEmail(), request.getPassword()),
				HttpStatus.OK);
	}

	@PostMapping("/register_customer")
	public ResponseEntity<?> createNewCustomerAccount(@RequestBody User newUser) {
		System.out.println("in cr new acct " + newUser);
		return new ResponseEntity<>(userService.createNewCustomerAccount(newUser), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<?> updateUserProfile(@RequestBody User user)
	{
		System.out.println("process update form "+user);//vendor : DETACHED POJO containing updated state
		
		return new ResponseEntity<>(userService.updateUserProfile(user), HttpStatus.CREATED);
	}
	
	@PostMapping("/profile/{userId}")
	public  ResponseEntity<?> getUserProfile(@PathVariable int userId)
	{
		System.out.println("get user profile "+userId);//vendor : DETACHED POJO containing updated state
		
		return new ResponseEntity<>(userService.getUserProfile(userId), HttpStatus.OK);
	}
}


















