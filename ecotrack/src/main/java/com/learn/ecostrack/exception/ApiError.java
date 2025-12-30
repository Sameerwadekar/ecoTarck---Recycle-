package com.learn.ecostrack.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
	private String path;
	private String error;
	private String errorMessage;
}
