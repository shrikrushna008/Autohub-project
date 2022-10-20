package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResponseDTO;
import com.app.service.IOrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderRestController {
	
	// dependency
	@Autowired
	IOrderService orderService;
	
	public OrderRestController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	@PostMapping("/place/{userId}")
	public ResponseDTO<?> placeOrder(@PathVariable int userId){
		System.out.println("in place order  " + userId + "ship address "  );
		try {
			orderService.placeOrder(userId);
			return new ResponseDTO<>(HttpStatus.OK, "Order Placed Succussfully",
					null);
		}catch (RuntimeException e) {
			System.out.println("err in place order "+e);
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to place order",
					null);
		}
	}
	
	@GetMapping("/myOrders/{userId}")
	public ResponseEntity<?> prevOrders(@PathVariable int userId){
		System.out.println("in get previous orders " + userId );
		return new ResponseEntity<>(orderService.prevOrders(userId), HttpStatus.OK);
	}
	
	@PostMapping("/cancel/{orderId}")
	public ResponseDTO<?> cancelOrder(@PathVariable int orderId){
		System.out.println("in cancel order  " + orderId  );
		try {
			String cancellationStatus= orderService.cancelOrder(orderId);
			return new ResponseDTO<>(HttpStatus.OK, "Order cancel : " + cancellationStatus,
					null);
		}catch (RuntimeException e) {
			System.out.println("err in place order "+e);
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, " Failed to cancel order ! ",
					null);
		}
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable int orderId){
		System.out.println("in get  order " + orderId );
		return new ResponseEntity<>(orderService.getSingleOrder(orderId), HttpStatus.OK);
	}
}
