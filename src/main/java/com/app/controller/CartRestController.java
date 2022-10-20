package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResponseDTO;
import com.app.pojos.Cart;
import com.app.service.ICartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartRestController {
	
	@Autowired
	private ICartService cartService;
	
	public CartRestController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	
	@PostMapping("/add_item")
	public ResponseEntity<?> addNewCartItem(@RequestBody Cart cart) {
		System.out.println("in cr new acct " + cart);
		return new ResponseEntity<>(cartService.addProductInCart(cart), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/remove_item/{cartId}")
	public ResponseDTO<?> removeCartItem(@PathVariable int cartId) {
		System.out.println("in remove cart item  " + cartId);
		try {
			cartService.removeCartItemById(cartId);
			return new ResponseDTO<>(HttpStatus.OK, "Cart item removed",
					null);
		}catch (RuntimeException e) {
			System.out.println("err in delete "+e);
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to remove cart item",
					null);
		}
	}
	
	@GetMapping("/get_cart/{userId}")
	public ResponseEntity<?> getCartDetails(@PathVariable int userId){
		System.out.println(" in  get cart details "+userId);
		return new ResponseEntity<>(cartService.getCartDetails(userId), HttpStatus.ACCEPTED);

	}
	
	
}
