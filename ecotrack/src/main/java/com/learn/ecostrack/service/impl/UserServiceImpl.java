package com.learn.ecostrack.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.learn.ecostrack.dto.UserDto;
import com.learn.ecostrack.entities.Role;
import com.learn.ecostrack.entities.User;
import com.learn.ecostrack.enums.AppRole;
import com.learn.ecostrack.exception.NotFoundException;
import com.learn.ecostrack.repositary.RoleRepositary;
import com.learn.ecostrack.repositary.UserRepositary;
import com.learn.ecostrack.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired private ModelMapper modelMapper;
	@Autowired private UserRepositary userRepositary;
	@Autowired private RoleRepositary roleRepositary;
	@Autowired private PasswordEncoder passwordEncoder;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		Role roleName = roleRepositary.findByAppRole(AppRole.ROLE_USER).orElseThrow(()->new NotFoundException("Role Not Found"));
		user.setRole(roleName);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepositary.save(user);
		UserDto savedDto = modelMapper.map(savedUser, UserDto.class);
		return savedDto;
	}

	
	
}
