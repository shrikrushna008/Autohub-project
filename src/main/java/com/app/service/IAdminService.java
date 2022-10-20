package com.app.service;

import java.util.List;

import com.app.pojos.Products;
import com.app.pojos.User;

public interface IAdminService {
	
	User createNewSupplierAccount(User newUser);

	List<User> getAllCustomer();
	List<User> getAllSupllier();

}
