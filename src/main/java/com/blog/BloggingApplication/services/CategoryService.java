package com.blog.BloggingApplication.services;

import java.util.List;
import java.util.Map;

import com.blog.BloggingApplication.dtos.CategoryDTO;
import jakarta.validation.Valid;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer CategoryId);
	
	void deleteCategory(Integer CategoryId);
	
	CategoryDTO getCategoryById(Integer CategoryId);
	
	List<CategoryDTO> getAllCategories();

	CategoryDTO updateCategoryPartially(@Valid Map<String, Object> fields, Integer categoryId);

}
