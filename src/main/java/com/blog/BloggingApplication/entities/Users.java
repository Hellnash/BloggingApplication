package com.blog.BloggingApplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity(name = "Peoples")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Users  implements UserDetails { //Users can be used as UserDetails
	
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
	Set<Role> roles = new HashSet<>(); //second column is reverse of first column as we are creating joins in user entity

	//Setting the Authorities based on roles
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).toList();
	}

	//Returning the username
	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
