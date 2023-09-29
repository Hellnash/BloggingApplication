package com.blog.BloggingApplication.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Peoples")
@NoArgsConstructor
@Data
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "Username", nullable = false, length = 100)
	private String name;
	private String email;
	private String password;
	private String about;
	
	/*
	 * a list of posts is mapped by a user and one to 
	 * many denotes that one "Users" can do many "Posts"
	 */
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Post> posts = new ArrayList<>();
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user-roles", //we are setting name of new table formed by joining role and user tables
	joinColumns = @JoinColumn(name = "Users", referencedColumnName = "id"), //first column name i.e user
	inverseJoinColumns = @JoinColumn(name = "Role", referencedColumnName = "id"))//second column name i.e role
	Set<Role> roles = new HashSet<>(); //second column is reverse of first column as we are creating joins in user 
									  //entity

}
