package com.learn.ecostrack.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecostrack.dto.UserDto;
import com.learn.ecostrack.entities.User;
import com.learn.ecostrack.repositary.UserRepositary;
import com.learn.ecostrack.security.LoginRequest;
import com.learn.ecostrack.security.LoginResponse;
import com.learn.ecostrack.security.jwt.JwtUtils;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private UserRepositary userRepositary;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authenticate = null;
		try {
			authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, password));
		} catch (BadCredentialsException e) {
			System.out.println("bad credentials");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		User user = (User) authenticate.getPrincipal();
		String token = jwtUtils.generateTokenFromUsername(user);
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(token);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		loginResponse.setUserDto(userDto);
		
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
}
