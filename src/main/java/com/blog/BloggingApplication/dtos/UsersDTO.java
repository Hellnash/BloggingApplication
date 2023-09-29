package com.blog.BloggingApplication.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsersDTO {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "name should be of at least 4 characters ")
	private String name;
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	@Size(min = 3, max= 10, message = "Password should be from 3 to 10 characters ")
	private String password;
	
	@NotEmpty
	@Size(min = 10, message = "About should be of at least 10 characters ")
	private String about;
}
