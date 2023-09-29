package com.blog.BloggingApplication.repositories;

import java.util.Optional;

import com.blog.BloggingApplication.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <Users, Integer> {
	
	Optional<Users> findByEmail(String email);

}
