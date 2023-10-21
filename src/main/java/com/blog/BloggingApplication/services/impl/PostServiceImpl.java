package com.blog.BloggingApplication.services.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.blog.BloggingApplication.dtos.PostDTO;
import com.blog.BloggingApplication.entities.Category;
import com.blog.BloggingApplication.entities.Post;
import com.blog.BloggingApplication.entities.Users;
import com.blog.BloggingApplication.exceptions.UserNotFoundException;
import com.blog.BloggingApplication.repositories.CategoryRepo;
import com.blog.BloggingApplication.repositories.PostRepo;
import com.blog.BloggingApplication.repositories.UserRepo;
import com.blog.BloggingApplication.services.PostService;
import com.blog.BloggingApplication.utils.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepo postRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	UserRepo userRepo;
	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
		Post createdPost = this.mapper.map(postDTO, Post.class);
		createdPost.setImageName("Default.png");
		createdPost.setAddedDate(new Date());
		//we extracted user and category based on their Ids from corresponding repositories
		Users postUser = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Users", "UserId", userId));
		createdPost.setUsers(postUser);
		Category postCategory = this.categoryRepo.findById(categoryId).orElseThrow(() -> new UserNotFoundException("Category", "CategoryId", categoryId));
		createdPost.setCategory(postCategory);
		Post savedPost = this.postRepo.save(createdPost);
		return this.mapper.map(savedPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new UserNotFoundException("Post", "PostId", postId));
		post.setTittle(postDTO.getTittle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		Post updatedPost = this.postRepo.save(post);
		return this.mapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new UserNotFoundException("Post", "PostId", postId));
		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		Sort sort;
		if(sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending(); // we will sort by value passed in sort by variable
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(page);
		List<Post> Posts = pagePost.getContent();
		List<PostDTO> PostDTOs = Posts.stream().map((post) -> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		PostResponse response = new PostResponse();
		response.setContent(PostDTOs);
		response.setPageNumber(pagePost.getNumber());
		response.setPageSize(pagePost.getSize());
		response.setTotalElements(pagePost.getTotalElements());
		response.setTotalPages(pagePost.getTotalPages());
		response.setLastPage(pagePost.isLast());
		return response;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new UserNotFoundException("Post", "PostId", postId));
		return this.mapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new UserNotFoundException("Category", "Category Id", categoryId));
		List<Post> Posts = this.postRepo.findByCategory(category);
		List<PostDTO> PostDTOs;
		PostDTOs = Posts.stream().map((post) -> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return PostDTOs;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer UserId) {
		Users users = this.userRepo.findById(UserId).orElseThrow(() -> new UserNotFoundException("Users", "userId", UserId));
		List<Post> Posts = this.postRepo.findByUsers(users);
		List<PostDTO> PostDTOs;
		PostDTOs = Posts.stream().map((post) -> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return PostDTOs;
	}

	@Override
	public List<PostDTO> searchPost(String Keyword) {
		List<Post> posts = this.postRepo.findByTittleContaining(Keyword);
        return posts.stream().map((post) -> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
	}

	@Override
	public PostDTO updatePostPartially(Map<String, Object> fields, Integer postId) {
		Post exsistingPost = this.postRepo.findById(postId).orElseThrow(()-> new UserNotFoundException("Post", "PostId", postId));
		fields.forEach((key,value) -> {//looping around key value pairs provided from request body
			Field field = ReflectionUtils.findRequiredField(Post.class, key);//find fields from keys in the Post class
			field.setAccessible(true);//Reflection utils opposes java so to avoid it we set field as accessible
			ReflectionUtils.setField(field, exsistingPost, value);//update found post fields with values provided
		});
		Post savedPost = this.postRepo.save(exsistingPost);
		return this.mapper.map(savedPost, PostDTO.class);
	}

}
