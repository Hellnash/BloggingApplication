package com.blog.BloggingApplication.services;

import java.util.List;
import java.util.Map;

import com.blog.BloggingApplication.dtos.UsersDTO;
import jakarta.validation.Valid;

public interface UserService {
	
	UsersDTO createUser(UsersDTO usersDTO);
	UsersDTO updateUser(UsersDTO usersDTO, Integer userID);
	UsersDTO getUserByID(Integer userID);
	List<UsersDTO> getAllUsers();
	void deleteUser(Integer userID);
	UsersDTO updateUserPartially(@Valid Map<String, Object> fields, Integer userId);
}
