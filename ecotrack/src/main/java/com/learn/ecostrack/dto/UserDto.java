package com.learn.ecostrack.dto;

import java.util.List;

import com.learn.ecostrack.entities.RecycleRequest;
import com.learn.ecostrack.entities.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String id;
	@NotNull(message = "provide name")
	@NotBlank(message = "name should not be blank")
	@Size(min = 3,max = 42)
	private String name;
	@NotNull(message = "provide email")
	@NotBlank(message = "email should not be blank")
	private String email;
	@NotNull(message = "provide password")
	@NotBlank(message = "password should not be blank")
	private String password;
	private String phoneNo;
	private int age;
	private List<RecycleRequest> recycleRequests;
	private Role role;
}
