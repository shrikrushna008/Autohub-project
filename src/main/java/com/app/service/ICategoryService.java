package com.app.service;

import java.util.List;

import com.app.pojos.Category;

public interface ICategoryService {
	
	Category addNewCategory(Category category);

	List<Category> getAllCategory();

	void removeCategoryById(int categoryId);

	Category updateCategory(Category category);
}
