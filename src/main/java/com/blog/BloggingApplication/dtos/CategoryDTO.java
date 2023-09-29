package com.blog.BloggingApplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 3, message = "Tittle should have at least 3 characters")
	private String categoryTittle;
	
	@NotBlank
	@Size(min = 10, message = "Description should have at least 10 characters")
	private String categoryDescription;
}
