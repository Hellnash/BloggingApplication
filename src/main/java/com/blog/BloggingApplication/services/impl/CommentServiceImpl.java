package com.blog.BloggingApplication.services.impl;

import com.blog.BloggingApplication.dtos.CommentDTO;
import com.blog.BloggingApplication.entities.Comment;
import com.blog.BloggingApplication.entities.Post;
import com.blog.BloggingApplication.entities.Users;
import com.blog.BloggingApplication.exceptions.UserNotFoundException;
import com.blog.BloggingApplication.repositories.CommentRepo;
import com.blog.BloggingApplication.repositories.PostRepo;
import com.blog.BloggingApplication.repositories.UserRepo;
import com.blog.BloggingApplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unused","unassigned"})
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId, Integer userId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new UserNotFoundException("Post" ,"postId", postId));
		Users users = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Users" ,"userId", userId));
		Comment comment = this.mapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		comment.setUsers(users);
		Comment savedComment = this.commentRepo.save(comment);
		return this.mapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new UserNotFoundException("Comment", "commentId", commentId));
		this.commentRepo.delete(comment);

	}

}
