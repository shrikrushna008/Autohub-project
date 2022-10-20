package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResponseDTO;
import com.app.pojos.Category;
import com.app.pojos.Products;
import com.app.service.IProductService;


@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductRestController {
	
	public ProductRestController() {
		System.out.println("in ctor of "+getClass().getName());
	}
 
	@Autowired
	private IProductService productService;
	
	@PostMapping("/add_product")
	public ResponseEntity<?> addNewCategory(@RequestBody Products product) {
		System.out.println("in cr new acct " + product);
		return new ResponseEntity<>(productService.addNewProduct(product), HttpStatus.CREATED);
	}
	
	@GetMapping("/get_products")
	public ResponseEntity<?> getAllProduct() {
		System.out.println("in get all products " );
		return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
	}
	
	@DeleteMapping("/remove_product/{productId}")
	public ResponseDTO<?> removeProductById(@PathVariable int productId) {
		System.out.println("in   removeProductById " + productId);
		try {
			productService.removeProductById(productId);
			return new ResponseDTO<>(HttpStatus.OK, "Cart item removed",
					null);
		}catch (RuntimeException e) {
			System.out.println("err in delete "+e);
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to remove cart item",
					null);
		}
	}
	
	@GetMapping("/{categoryName}")
	public ResponseEntity<?> getCartDetails(@PathVariable String categoryName){
		System.out.println(" in  get cart details "+categoryName);
		return new ResponseEntity<>(productService.getAllProductByCategory(categoryName), HttpStatus.ACCEPTED);

	}
	
	@PostMapping("/update")
	public  ResponseEntity<?> updateProduct(@RequestBody Products product)
	{
		System.out.println("process update form "+product);//product : DETACHED POJO containing updated state
		
		return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.CREATED);
	}
}
