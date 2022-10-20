package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_excs.ResourceNotFoundException;
import com.app.dao.CartRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Cart;
import com.app.pojos.User;


@Service
@Transactional
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Cart addProductInCart(Cart cart) {
		System.out.println("cart details " + cart);
		return cartRepo.save(cart);
	}
	
	@Override
	public void removeCartItemById(int cartId) {
		
		boolean exists = cartRepo.existsById(cartId);
		if(!exists)
			throw new ResourceNotFoundException("Invalid user id");
		System.out.println("in remove cart item  " + cartId);
		cartRepo.deleteById(cartId);
		return;
		
	}
	
	@Override
	public List<Cart> getCartDetails(int userId) {
		
		return cartRepo.findByUser(userRepo.findById(userId));
	}
}
