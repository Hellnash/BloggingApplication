package com.blog.BloggingApplication.controllers;

import java.util.List;
import java.util.Map;

import com.blog.BloggingApplication.constants.AppConstants;
import com.blog.BloggingApplication.constants.UrlConstants;
import com.blog.BloggingApplication.dtos.PostDTO;
import com.blog.BloggingApplication.services.PostService;
import com.blog.BloggingApplication.utils.ApiResponse;
import com.blog.BloggingApplication.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping(UrlConstants.CREATE_POST)
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO,
			                                  @PathVariable Integer userId,
			                                  @PathVariable Integer categoryId
			                                  ){
		PostDTO createdPost = this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}
	
	@GetMapping(UrlConstants.POST_BY_USER)
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer UserId){
		List<PostDTO> postsByUser = this.postService.getPostByUser(UserId);
		return new ResponseEntity<>(postsByUser, HttpStatus.OK);
	}
	
	@GetMapping(UrlConstants.POST_BY_CATEGORY)
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer CategoryId){
		List<PostDTO> postsInCategory = this.postService.getPostByCategory(CategoryId);
		return new ResponseEntity<>(postsInCategory, HttpStatus.OK);
	}
	
	@GetMapping(UrlConstants.ALL_POSTS)
	public ResponseEntity<PostResponse> getAllPosts(
				@RequestParam (value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) 
				Integer pageNumber,
				@RequestParam (value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) 
				Integer pageSize,
				@RequestParam (value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) 
				String sortBy,
				@RequestParam (value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false)
				String sortDirection
			  ){
		
		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@GetMapping(UrlConstants.SINGLE_POST)
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer PostId){
		PostDTO postById = this.postService.getPostById(PostId);
		return new ResponseEntity<>(postById, HttpStatus.OK);
	}
	
	@PutMapping(UrlConstants.CHANGE_POST)
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer PostId){
		PostDTO updatedPost = this.postService.updatePost(postDTO, PostId);
		return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
	}
	
	@PatchMapping(UrlConstants.CHANGE_POST)
	public ResponseEntity<PostDTO> updatePostPartially(@Valid @RequestBody Map<String,Object> fields, @PathVariable Integer PostId){
		PostDTO partiallyUpdatedPost = this.postService.updatePostPartially(fields,PostId);
		return ResponseEntity.ok(partiallyUpdatedPost);
		
	}
	
	@DeleteMapping(UrlConstants.CHANGE_POST)
	public ApiResponse deletePost(@PathVariable Integer PostId){
		this.postService.deletePost(PostId);
		return new ApiResponse("Post is successfully Deleted !! ", true);
	}
	
	@GetMapping(UrlConstants.SEARCH_POST)
	public ResponseEntity<List<PostDTO>> searchPost(@PathVariable String keyword){
		List<PostDTO> foundPosts = this.postService.searchPost(keyword);
		return new ResponseEntity<>(foundPosts,HttpStatus.OK);
	}

}