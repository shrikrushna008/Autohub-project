package com.app.pojos;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Products extends BaseEntity {
	@Column(length = 45)
	// @NotBlank(message = "Product name must be supplied")
	private String name;
	@Column(length = 150)
	private String description;
	@Column(length = 10, precision = 2)
	// @NotBlank(message = "Product price must be supplied")
	private double price;
	@Column(length = 10, precision = 2)
	private double weight;
	@Lob
	private byte[] thumbnails;
	@Lob
	private byte[] image;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
	@JsonIgnoreProperties("products")
	private Category category;

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
	@JsonIgnoreProperties("products")
	private User supplier;
	
	@Column(length = 10)
	private int stock;
	@Column(name="create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "invalid create date")
	private LocalDate createDate;
	public Products() {
		super();
	}
	
	public Products(@NotBlank(message = "Product name must be supplied") String name, String description,
			@NotBlank(message = "Product price must be supplied") double price, double weight, byte[] thumbnails,
			byte[] image, Category category, int stock,
			@PastOrPresent(message = "invalid create date") LocalDate createDate) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.weight = weight;
		this.thumbnails = thumbnails;
		this.image = image;
		this.category = category;
		this.stock = stock;
		this.createDate = createDate;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public byte[] getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(byte[] thumbnails) {
		this.thumbnails = thumbnails;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@JsonIgnore //ignore this property during ser.
	public Category getCategory() {
		return category;
	}
	@JsonProperty //DO NOT ignore this property during de-ser (since you want to assign customer id as FK)
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
	@JsonIgnore
	public User getSupplier() {
		return supplier;
	}
	
	@JsonProperty
	public void setSupplier(User supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Products [name=" + name + ", description=" + description + ", price=" + price + ", weight=" + weight
				+ ", thumbnails=" + Arrays.toString(thumbnails) + ", image=" + Arrays.toString(image) + ", category="
				+ category + ", supplier=" + supplier + ", stock=" + stock + ", createDate=" + createDate + "]";
	}
	
	
}
