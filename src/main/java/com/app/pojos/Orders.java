package com.app.pojos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Orders extends BaseEntity {
	
	@Column(length = 10, precision = 2)
	private double amount;
	
	private String email;
	
	@Column(name = "shipping_address",length = 255)
	private String shippingAddress;
	@Column(name = "order_address",length = 255)
	private String orderAddress;
	@Column(name = "order_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "invalid order date")
	private LocalDate orderDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
	@JsonIgnoreProperties("orders")
	@JsonIgnore //ignore this property during both ser n de-serial
	private User user;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("order")//To tell SC : ignore property of list of accts during ser n de-serial .
	Set<OrderDetails> orderItems = new HashSet<>();
	
	public Orders() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Orders(double amount, String email, String shippingAddress, String orderAddress,
			@PastOrPresent(message = "invalid order date") LocalDate orderDate, User user, Status status,
			Set<OrderDetails> orderItems) {
		super();
		this.amount = amount;
		this.email = email;
		this.shippingAddress = shippingAddress;
		this.orderAddress = orderAddress;
		this.orderDate = orderDate;
		this.user = user;
		this.status = status;
		this.orderItems = orderItems;
	}



	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
	
	@JsonProperty
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Set<OrderDetails> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderDetails> orderItems) {
		this.orderItems = orderItems;
	}
	
	@Override
	public String toString() {
		return "Orders [amount=" + amount + ", email=" + email + ", shippingAddress=" + shippingAddress
				+ ", orderAddress=" + orderAddress + ", orderDate=" + orderDate + ", user=" + user + ", status="
				+ status + ", orderItems=" + orderItems + ", getId()=" + getId() + "]";
	}
	
	
	
}
