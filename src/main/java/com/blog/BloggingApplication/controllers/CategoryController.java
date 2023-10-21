package com.blog.BloggingApplication.controllers;

import java.util.List;
import java.util.Map;

import com.blog.BloggingApplication.constants.UrlConstants;
import com.blog.BloggingApplication.dtos.CategoryDTO;
import com.blog.BloggingApplication.services.CategoryService;
import com.blog.BloggingApplication.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
@SuppressWarnings("unused")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping(UrlConstants.DEFAULT_CATEGORY)
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO createdCategory = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}
	
	@PutMapping(UrlConstants.CHANGE_CATEGORY)
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer CategoryId){
		CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO, CategoryId);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@PatchMapping(UrlConstants.CHANGE_CATEGORY)
	public ResponseEntity<CategoryDTO> updateCategoryPartially(@Valid @RequestBody Map<String,Object> fields, @PathVariable Integer categoryId){
		CategoryDTO partiallyUpdatedCategory = this.categoryService.updateCategoryPartially(fields,categoryId);
		return new ResponseEntity<>(partiallyUpdatedCategory,HttpStatus.CREATED); 
	}
	
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer CategoryId){
		this.categoryService.deleteCategory(CategoryId);
		return new ResponseEntity<> (new ApiResponse("Category is deleted ", false), HttpStatus.OK);
	}
	
	@GetMapping(UrlConstants.CHANGE_CATEGORY)
	public ResponseEntity<CategoryDTO> getACategory(@PathVariable Integer CategoryId){
		CategoryDTO requiredCategory = this.categoryService.getCategoryById(CategoryId);
		return ResponseEntity.ok(requiredCategory);
	}
	
	@GetMapping(UrlConstants.DEFAULT_CATEGORY)
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		List<CategoryDTO> allCategories = this.categoryService.getAllCategories();
		return new ResponseEntity<> (allCategories, HttpStatus.OK);
	}
	
}