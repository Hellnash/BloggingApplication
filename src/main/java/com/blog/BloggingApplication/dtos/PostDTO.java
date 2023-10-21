package com.blog.BloggingApplication.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
	
	private Integer postId;
	@NotEmpty
	@Size(max = 10, message = "tittle is too long")
	private String tittle;
	@NotEmpty
	@Size(min = 4, message = "name should be of at least 4 characters ")
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDTO category;
	private UsersDTO user;
	private CommentDTO comments;

}
