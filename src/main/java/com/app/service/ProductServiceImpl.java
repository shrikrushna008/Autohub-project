package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_excs.ResourceNotFoundException;
import com.app.dao.CategoryRepository;
import com.app.dao.ProductRespository;
import com.app.pojos.Products;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductRespository productRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	
	@Override
	public Products addNewProduct(Products product) {
		System.out.println("in add new product");
		return productRepo.save(product);
	}


	@Override
	public List<Products> getAllProduct() {
		System.out.println("in get all products");
		return productRepo.findAll();
	}
	
	
	@Override
	public void removeProductById(int productId) {
		boolean exists = productRepo.existsById(productId);
		if(!exists)
			throw new ResourceNotFoundException("Invalid user id");
		System.out.println("in remove product item  " + productId);
		productRepo.deleteById(productId);
		return;
	}


	@Override
	public List<Products> getAllProductByCategory(String categoryName) {
		
		return productRepo.findByCategory(categoryRepo.findByName(categoryName));
	}
	
	@Override
	public Products updateProduct(Products product) {
		System.out.println("in update product "+product);
		return productRepo.save(product);
	}
	
	
}
