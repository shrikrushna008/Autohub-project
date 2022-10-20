package com.app.pojos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "order_details")

public class OrderDetails extends BaseEntity{
	
	private double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
	private Products product;
	
	private int quantity;	
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
	@JsonIgnoreProperties("orderItems")
	@JsonIgnore //ignore this property during both ser n de-serial
	private Orders order;

	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public OrderDetails(double price, Products product, int quantity, Status status, Orders order) {
		super();
		this.price = price;
		this.product = product;
		this.quantity = quantity;
		this.status = status;
		this.order = order;
	}



	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Products getProduct() {
		return product;
	}


	public void setProduct(Products product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@JsonIgnore
	public Orders getOrder() {
		return order;
	}

	@JsonProperty
	public void setOrder(Orders order) {
		this.order = order;
	}



	@Override
	public String toString() {
		return "OrderDetails [price=" + price + ", product=" + product + ", quantity=" + quantity + ", status=" + status
				+ ", order=" + order + ", getId()=" + getId() + "]";
	}

	
	
	
}
