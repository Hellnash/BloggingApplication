package com.blog.BloggingApplication.config;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.blog.BloggingApplication.entities.Role;
import com.blog.BloggingApplication.entities.Users;
import com.blog.BloggingApplication.exceptions.UserNotFoundException;
import com.blog.BloggingApplication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class CustomUserDetails implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users users = userRepo.findByEmail(username).orElseThrow( () -> new UserNotFoundException("Users","email"+username,0));
		return new User(users.getEmail(), //Username
						users.getPassword(), //Password
						getAuthorities(users.getRoles())); //Authority of the user
	}
	
 public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {

     return roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
	}

}
