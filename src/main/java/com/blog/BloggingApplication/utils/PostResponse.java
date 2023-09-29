package com.blog.BloggingApplication.utils;

import com.blog.BloggingApplication.dtos.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {
	
	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private long totalPages;
	private boolean lastPage;

}
