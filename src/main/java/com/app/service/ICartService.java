package com.app.service;

import java.util.List;

import com.app.pojos.Cart;

public interface ICartService {
	
	Cart addProductInCart(Cart cart);

	void removeCartItemById(int cartId);

	List<Cart> getCartDetails(int userId);
}
