package com.blog.BloggingApplication.controllers;

import java.util.List;
import java.util.Map;

import com.blog.BloggingApplication.constants.UrlConstants;
import com.blog.BloggingApplication.dtos.UsersDTO;
import com.blog.BloggingApplication.services.UserService;
import com.blog.BloggingApplication.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@SuppressWarnings("unused")
public class UsersController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(UrlConstants.DEFAULT_USER)
	public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody UsersDTO userDTO){
		
		UsersDTO createdUser = this.userService.createUser(userDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@PutMapping(UrlConstants.CHANGE_USER)
	public ResponseEntity<UsersDTO> updateUser(@Valid @RequestBody UsersDTO userDTO,
											   @PathVariable Integer UserId){
		
		UsersDTO updatedUser = this.userService.updateUser(userDTO, UserId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@PatchMapping(UrlConstants.CHANGE_USER)
	public ResponseEntity<UsersDTO> updateUserPartially(@Valid @RequestBody Map<String,Object> fields,
														@PathVariable Integer UserId){

		UsersDTO partiallyUpdatedUser = this.userService.updateUserPartially(fields,UserId);
		return new ResponseEntity<>(partiallyUpdatedUser, HttpStatus.CREATED);
	}
	
	@DeleteMapping(UrlConstants.CHANGE_USER)
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("UserId") Integer uid){
		
		this.userService.deleteUser(uid);
		return new ResponseEntity<> (new ApiResponse("Users is deleted Successfully", true),HttpStatus.OK);
	}
	
	@GetMapping(UrlConstants.DEFAULT_USER)
	public ResponseEntity<List<UsersDTO>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping(UrlConstants.CHANGE_USER)
	public ResponseEntity<UsersDTO> getUser(@PathVariable Integer UserId){
		return ResponseEntity.ok(this.userService.getUserByID(UserId));
	}

}
