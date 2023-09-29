package com.blog.BloggingApplication.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
	
	private Integer commentId;
	
	@NotEmpty
	@Size(min = 4, message = "name should be of at least 4 characters ")
	private String content;
	
	private UsersDTO user;

}
