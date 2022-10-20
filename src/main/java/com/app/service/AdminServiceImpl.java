package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.pojos.Role;
import com.app.pojos.User;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
	
	// dependency
	@Autowired()
	private UserRepository userRepo;
	

	@Override
	public List<User> getAllCustomer() {
		// TODO Auto-generated method stub
		return userRepo.findByRole(Role.CUSTOMER);
	}

	@Override
	public List<User> getAllSupllier() {
		// TODO Auto-generated method stub
		return userRepo.findByRole(Role.SUPPLIER);
	}
	
	@Override
	public User createNewSupplierAccount(User newUser) {
		newUser.setRole(Role.SUPPLIER);
		return userRepo.save(newUser);
	}
	 
}
