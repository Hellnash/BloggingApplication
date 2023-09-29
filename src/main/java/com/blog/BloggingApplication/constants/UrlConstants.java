package com.blog.BloggingApplication.constants;

public class UrlConstants {
	
	//Post
	public static final String CREATE_POST = "/user/{userId}/category/{categoryId}/post";
	public static final String POST_BY_CATEGORY = "/category/{CategoryId}/posts";
	public static final String POST_BY_USER = "/user/{UserId}/posts";
	public static final String ALL_POSTS = "/posts";
	public static final String SINGLE_POST = "/post/{PostId}";
	public static final String CHANGE_POST = "/post/{PostId}";
	public static final String SEARCH_POST = "/post/search/{keyword}";
	
	//Users
	public static final String DEFAULT_USER = "/";
	public static final String CHANGE_USER = "/{UserId}";
	
	//Category
	public static final String DEFAULT_CATEGORY = "/";
	public static final String CHANGE_CATEGORY = "/{CategoryId}";
	
	//Comment
	public static final String CREATE_COMMENT = "post/{postId}/user/{userId}/comment";
	public static final String DELETE_COMMENT = "/comment/{commentId}";
	
	//JWT
	public static String TOKEN = "/generateToken";
	
	
}
