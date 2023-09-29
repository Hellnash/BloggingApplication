package com.blog.BloggingApplication.services.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.blog.BloggingApplication.dtos.CategoryDTO;
import com.blog.BloggingApplication.entities.Category;
import com.blog.BloggingApplication.exceptions.UserNotFoundException;
import com.blog.BloggingApplication.repositories.CategoryRepo;
import com.blog.BloggingApplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo repository;
	@Autowired
	ModelMapper mapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {

		Category category = this.mapper.map(categoryDTO, Category.class);
		Category createdUser = this.repository.save(category);
		return this.mapper.map(createdUser, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer CategoryId) {

		Category foundCategory = this.repository.findById(CategoryId).orElseThrow(()-> new UserNotFoundException("Category", "CategoryId", CategoryId));
		foundCategory.setCategoryTittle(categoryDTO.getCategoryTittle());
		foundCategory.setCategoryDescription(categoryDTO.getCategoryDescription());
		Category updatedCategory = this.repository.save(foundCategory);
		return this.mapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer CategoryId) {

		Category deleteCategory = this.repository.findById(CategoryId).orElseThrow(()-> new UserNotFoundException("Category", "CategoryId", CategoryId));
		this.repository.delete(deleteCategory);
	}

	@Override
	public CategoryDTO getCategoryById(Integer CategoryId) {

		Category foundCategory = this.repository.findById(CategoryId).orElseThrow(()-> new UserNotFoundException("Category", "CategoryId", CategoryId));
		return this.mapper.map(foundCategory, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {

		/*
		 * the list is converted to stream and mapped to the model
		 * mapper to convert each category object into DTO object
		 */

        return this.repository.findAll().stream().map(category -> this.mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO updateCategoryPartially(Map<String, Object> feilds, Integer categoryId) {
		
		Category exsistingCategory = this.repository.findById(categoryId).orElseThrow(()-> new UserNotFoundException("Category", "CategoryId", categoryId));
		feilds.forEach((key,value) -> {
			Field field = ReflectionUtils.findRequiredField(Category.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, exsistingCategory, value);
		});
		Category savedCategory = this.repository.save(exsistingCategory);
		return this.mapper.map(savedCategory, CategoryDTO.class);
	}

}