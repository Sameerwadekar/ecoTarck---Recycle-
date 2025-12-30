package com.learn.ecostrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecostrack.dto.UserDto;
import com.learn.ecostrack.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
		UserDto saveUser = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(saveUser,HttpStatus.OK);
	}
}
