package com.blog.BloggingApplication.controllers;

import com.blog.BloggingApplication.constants.UrlConstants;
import com.blog.BloggingApplication.dtos.CommentDTO;
import com.blog.BloggingApplication.services.CommentService;
import com.blog.BloggingApplication.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping(UrlConstants.CREATE_COMMENT)
	public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO,
													@PathVariable Integer postId,
													@PathVariable Integer userId){
		CommentDTO postedComment = this.commentService.createComment(commentDTO, postId, userId);
		return new ResponseEntity<>(postedComment, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping(UrlConstants.DELETE_COMMENT)
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		
		this.commentService.deleteComment(commentId);
		ApiResponse response = new ApiResponse("Comment is successfully Deleted", true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
