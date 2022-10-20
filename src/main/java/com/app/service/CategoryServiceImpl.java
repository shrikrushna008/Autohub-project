package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_excs.ResourceNotFoundException;
import com.app.dao.CategoryRepository;
import com.app.pojos.Category;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	@Override
	public Category addNewCategory(Category category) {
		
		return categoryRepo.save(category);
	}
	
	@Override
	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}
	
	@Override
	public void removeCategoryById(int categoryId) {
		boolean exists = categoryRepo.existsById(categoryId);
		if(!exists)
			throw new ResourceNotFoundException("Invalid user id");
		System.out.println("in remove product item  " + categoryId);
		categoryRepo.deleteById(categoryId);
		return;
	}
	
	@Override
	public Category updateCategory(Category category) {
		System.out.println("in update category "+category);
	
		return categoryRepo.save(category);
	}
}
