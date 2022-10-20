package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity{
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
	private Products product;
	
	@Column(nullable = false)
	private int quantity;


	public Cart() {
		// TODO Auto-generated constructor stub	
	}



	public Cart(User user, Products product, int quantity) {
		super();
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Products getProduct() {
		return product;
	}



	public void setProduct(Products product) {
		this.product = product;
	}



	@Override
	public String toString() {
		return "Cart [user=" + user + ", product=" + product + ", quantity=" + quantity + "]";
	}


	
	
	
	
	
	
	
	
}
