package com.blog.BloggingApplication.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Posts")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	@Column(name = "post_title ", nullable = false, length = 100)
	private String tittle;
	@Column(length = 10000)
	private String content;
	private String imageName;
	private Date addedDate;
	/*a users and a category are mapped by a list of posts and many to
	 * one denotes that many "Posts" are done by a users and many "Posts"
	 * is present under one "Category"
	 */
	@ManyToOne
	@JoinColumn(name = "category_id") //name of the column in the database
	private Category category;
	@ManyToOne
	private Users users;
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
}

