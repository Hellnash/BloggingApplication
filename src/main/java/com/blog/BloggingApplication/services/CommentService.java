package com.blog.BloggingApplication.services;

import com.blog.BloggingApplication.dtos.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO comment, Integer PostId, Integer userId);
	void deleteComment(Integer commentId);

}
