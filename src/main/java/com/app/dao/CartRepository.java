package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Cart;
import com.app.pojos.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUser(Optional<User> optional);
	
	
}
