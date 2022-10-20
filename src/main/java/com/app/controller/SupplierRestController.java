package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Status;
import com.app.service.ISupplierService;

@RestController
@RequestMapping("/supplier")
@CrossOrigin
public class SupplierRestController {
	
	// dependency 
	@Autowired
	ISupplierService supplierService ;
	
	public SupplierRestController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	@GetMapping("/get_products/{supplierId}")
	public ResponseEntity<?> getAllProduct(@PathVariable int supplierId ) {
		System.out.println("in get all products by supplier " + supplierId  );
		return new ResponseEntity<>(supplierService.getAllProduct(supplierId), HttpStatus.OK);
	}
	
	@GetMapping("/get_allOrders/{supplierId}")
	public ResponseEntity<?> getAllOrders(@PathVariable int supplierId ) {
		System.out.println("in get all orders by supplier " + supplierId  );
		return new ResponseEntity<>(supplierService.getAllOrders(supplierId), HttpStatus.OK);
	}
	
	@GetMapping("/get_currOrders/{supplierId}")
	public ResponseEntity<?> getCurrentOrders(@PathVariable int supplierId ) {
		System.out.println("in get curr orders by supplier " + supplierId  );
		return new ResponseEntity<>(supplierService.getCurrentOrders(supplierId), HttpStatus.OK);
	}

	@GetMapping("/get_currOrderDetails/{itemId}")
	public ResponseEntity<?> processCurrOrder(@PathVariable int itemId ) {
		System.out.println("in get curr order item  " + itemId  );
		return new ResponseEntity<>(supplierService.processCurrOrder(itemId), HttpStatus.OK);
	}
	
	@PostMapping("/setOrderStatus/{itemId}")
	public String setOrderStatus(@PathVariable int itemId ) {
		System.out.println("in set order item  status  " + itemId  );
		return supplierService.setOrderStatus(itemId, Status.DELIVERED);
	}
}
