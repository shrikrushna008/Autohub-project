package com.app.service;

import java.util.List;

import com.app.pojos.Products;

public interface IProductService {

	Products addNewProduct(Products product);

	List<Products> getAllProduct();

	void removeProductById(int productId);
	
	List<Products> getAllProductByCategory(String categoryName);

	Products updateProduct(Products product);
}
