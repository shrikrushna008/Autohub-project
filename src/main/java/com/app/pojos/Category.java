package com.app.pojos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Category extends BaseEntity {
	@Column(length = 45)
	@NotBlank(message = "Category name must be supplied")
	private String name;
	@Column(length = 150)
	private String description;
	@Lob
	private byte[] thumbnails;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("category")//To tell SC : ignore property of list of accts during ser n de-serial .
    private Set<Products> products = new HashSet<Products>();
	
	public Category() {
		super();
	}

	

	public Category(@NotBlank(message = "Category name must be supplied") String name, String description,
			byte[] thumbnails, Set<Products> products) {
		super();
		this.name = name;
		this.description = description;
		this.thumbnails = thumbnails;
		this.products = products;
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

	public byte[] getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(byte[] thumbnails) {
		this.thumbnails = thumbnails;
	}
	
	

	public Set<Products> getProducts() {
		return products;
	}



	public void setProducts(Set<Products> products) {
		this.products = products;
	}



	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + ", thumbnails=" + Arrays.toString(thumbnails)
				+ ", getId()=" + getId() + "]";
	}
	
}
