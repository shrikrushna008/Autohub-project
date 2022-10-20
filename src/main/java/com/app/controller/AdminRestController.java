package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.User;
import com.app.service.IAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminRestController {
	
	//dependency
	@Autowired
	IAdminService adminService;
	
	public AdminRestController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	@PostMapping("/register_supplier")
	public ResponseEntity<?> createNewCustomerAccount(@RequestBody User newUser) {
		System.out.println("in cr new supplier acct " + newUser);
		return new ResponseEntity<>(adminService.createNewSupplierAccount(newUser), HttpStatus.CREATED);
	}
	
	@GetMapping("/get_customers")
	public ResponseEntity<?> getAllCustomer() {
		System.out.println("in get all Customers " );
		return new ResponseEntity<>(adminService.getAllCustomer(), HttpStatus.OK);
	}
	
	@GetMapping("/get_suppliers")
	public ResponseEntity<?> getSupplier() {
		System.out.println("in get all Suppliers " );
		return new ResponseEntity<>(adminService.getAllSupllier(), HttpStatus.OK);
	}
	

}
