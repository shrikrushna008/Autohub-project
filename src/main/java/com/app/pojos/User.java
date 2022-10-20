package com.app.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	
	@Column(length = 20)
	@NotBlank(message = "name must be supplied")
	private String name="abc";
	@Column(length = 20,unique = true)
	@NotBlank(message = "Email is required")
	@Length(min = 5,max=20,message = "Invalid Email length")
	@Email(message = "Invalid email format")
	private String email="abc@gmail.com";
	@Column(length = 20,nullable = false)
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Invalid password!")
//	@JsonIgnore
	private String password;
	@NotBlank(message = "name must be supplied")
	private String address;
	@Column(length = 10 , name = "pin_code")
	private String pinCode;
	@Column(length = 15)
	private String phone;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("user")//To tell SC : ignore property of list of accts during ser n de-serial .
	Set<Orders> orders = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "supplier", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("supplier")//To tell SC : ignore property of list of accts during ser n de-serial .
	Set<Products> products = new HashSet<>();
	
	
	public User() {
		super();
	}

	

	public User(@NotBlank(message = "name must be supplied") String name,
			@NotBlank(message = "Email is required") @Length(min = 5, max = 20, message = "Invalid Email length") @Email(message = "Invalid email format") String email,
			@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid password!") String password,
			@NotBlank(message = "name must be supplied") String address, String pinCode, String phone, Role role,
			Set<Orders> orders, Set<Products> products) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.phone = phone;
		this.role = role;
		this.orders = orders;
		this.products = products;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	public Set<Products> getProducts() {
		return products;
	}

	public void setProducts(Set<Products> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", address=" + address
				+ ", pinCode=" + pinCode + ", phone=" + phone + ", role=" + role + ", orders=" + orders + ", products="
				+ products + ", getId()=" + getId() + "]";
	}

	
	
	
	
}






















