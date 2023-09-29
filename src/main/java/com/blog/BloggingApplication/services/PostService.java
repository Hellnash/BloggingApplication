package com.blog.BloggingApplication.services;

import java.util.List;
import java.util.Map;

import com.blog.BloggingApplication.dtos.PostDTO;
import com.blog.BloggingApplication.utils.PostResponse;
import jakarta.validation.Valid;

public interface PostService {
	
	PostDTO createPost(PostDTO postDTO, Integer UserId, Integer CategoryId);
	
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	void deletePost(Integer PostId);
	
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	
	PostDTO getPostById(Integer postId);
	
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	List<PostDTO> getPostByUser(Integer UserId);
	
	List<PostDTO> searchPost(String Keyword);

	PostDTO updatePostPartially(@Valid Map<String, Object> fields, Integer postId);

}
