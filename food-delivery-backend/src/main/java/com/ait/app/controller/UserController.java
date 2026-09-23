package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.UserDto;
import com.ait.app.model.Address;
import com.ait.app.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("addUser")
	ResponseEntity addUser(@RequestBody UserDto userDto) {
	userService.saveUser(userDto);
		return new ResponseEntity("user saved",HttpStatus.CREATED);
	}
	

}
