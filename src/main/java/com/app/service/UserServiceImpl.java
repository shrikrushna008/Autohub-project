package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_excs.UserHandlingException;
import com.app.dao.UserRepository;
import com.app.pojos.Role;
import com.app.pojos.User;


@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired()
	private UserRepository userRepo;
	@Override
	public User authenticateUser(String email, String password) {

		return userRepo.authenticateUser(email, password).
				orElseThrow(() -> new UserHandlingException("Invalid Credentials!!!!"));
	}
	
	@Override
	public User createNewCustomerAccount(User newUser) {
		newUser.setRole(Role.CUSTOMER);
		System.out.println("in create new account "+newUser);
		return userRepo.save(newUser);
	}

	@Override
	public User updateUserProfile(User user) {
		System.out.println("in update User Profile "+user);
		return userRepo.save(user);
	}
	
	@Override
	public User getUserProfile(int userId) {
		
		return userRepo.findById(userId).orElseThrow(() -> new UserHandlingException("User Dosen't exist"));
	}
}
