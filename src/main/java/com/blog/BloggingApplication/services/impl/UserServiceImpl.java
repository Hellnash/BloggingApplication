package com.blog.BloggingApplication.services.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.blog.BloggingApplication.dtos.UsersDTO;
import com.blog.BloggingApplication.entities.Users;
import com.blog.BloggingApplication.exceptions.UserNotFoundException;
import com.blog.BloggingApplication.repositories.UserRepo;
import com.blog.BloggingApplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;


import jakarta.validation.Valid;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo repository;
	@Autowired
	ModelMapper mapper;
    
	/*1. Converting dto taken from the client to entity
	 *2. Saving the entity in the database via repository
	 *3. Returning dto back to the clients post converting entity back to dto*/
	
	@Override
	public UsersDTO createUser(UsersDTO usersDTO) {
		
		Users users = this.DTOtoUsers(usersDTO);				//1
		Users savedUser = this.repository.save(users);			//2
		return this.UsersToDTO(savedUser);						//3
	}

	@Override
	public UsersDTO updateUser(UsersDTO usersDTO, Integer userID) {
		
		Users users = this.repository.findById(userID).orElseThrow(() -> new UserNotFoundException("Users","Id",userID));
		users.setName(usersDTO.getName());
		users.setEmail(usersDTO.getEmail());
		users.setPassword(usersDTO.getPassword());
		users.setAbout(usersDTO.getAbout());
		Users updatedUser = this.repository.save(users);
		return this.UsersToDTO(updatedUser);
	}

	@Override
	public UsersDTO getUserByID(Integer userID) {

		Users users = this.repository.findById(userID).orElseThrow(() -> new UserNotFoundException("Users","Id",userID));
		return this.UsersToDTO(users);
	}

	@Override
	public List<UsersDTO> getAllUsers() {

        return this.repository.findAll().stream().map(this::UsersToDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userID) {

		Users users = this.repository.findById(userID).orElseThrow(() -> new UserNotFoundException("Users","Id",userID));
		this.repository.delete(users);

	}
	
	/*
	 * Users and user DTO is required so that we can hide the entity users
	 *from the clients that is storing the data in the database via repository 
	 *and user dto to share the data to the clients like masking
	 */
	
	private Users DTOtoUsers(UsersDTO dto) {
		
		//converting UsersDTo object to Users object
        return this.mapper.map(dto, Users.class);
		
	}
	
   public UsersDTO UsersToDTO(Users users) {
	   return this.mapper.map(users, UsersDTO.class);
	}

@Override
public UsersDTO updateUserPartially(@Valid Map<String, Object> fields, Integer userId) {
	
	Users existingUser = this.repository.findById(userId).orElseThrow(()-> new UserNotFoundException("Users","Id",userId));
	fields.forEach((key,value)->{ //run a loop in the key value pairs provided from request body
		Field field = ReflectionUtils.findRequiredField(Users.class, key);//find fields based on key from user object
		field.setAccessible(true);//Reflection utils opposes java so to avoid it we set field as accessible
		ReflectionUtils.setField(field, existingUser, value);//set fields obtained with value in the existing object
	});
	
	Users SavedPartialUser = this.repository.save(existingUser);
	return this.mapper.map(SavedPartialUser, UsersDTO.class);
	}

}
