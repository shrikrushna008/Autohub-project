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
import com.app.pojos.User;
import com.app.service.ICategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryRestController {
	
	@Autowired
	private ICategoryService categoryService;
	
	public CategoryRestController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	@PostMapping("/add_category")
	public ResponseEntity<?> addNewCategory(@RequestBody Category category) {
		System.out.println("in cr new acct " + category);
		return new ResponseEntity<>(categoryService.addNewCategory(category), HttpStatus.CREATED);
	}
	
	@GetMapping("/get_category")
	public ResponseEntity<?> getAllCategory() {
		System.out.println("in get all category " );
		return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
	}
	
	@DeleteMapping("/remove_category/{categoryId}")
	public ResponseDTO<?> removeCategoryById(@PathVariable int categoryId) {
		System.out.println("in   removeProductById " + categoryId);
		try {
			categoryService.removeCategoryById(categoryId);
			return new ResponseDTO<>(HttpStatus.OK, "Category removed",
					null);
		}catch (RuntimeException e) {
			System.out.println("err in delete "+e);
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to remove category",
					null);
		}
	}
	
	@PostMapping("/update")
	public  ResponseEntity<?> updateCategory(@RequestBody Category category)
	{
		System.out.println("process update form "+category);//category : DETACHED POJO containing updated state
		
		return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.CREATED);
	}
	
}









