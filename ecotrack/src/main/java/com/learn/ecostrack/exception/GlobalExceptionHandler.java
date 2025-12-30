package com.learn.ecostrack.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(exception = NotFoundException.class)
	public ResponseEntity<ApiError> notFoundExceptionHandler(NotFoundException ex,HttpServletRequest httpRequest){
		ApiError apiError = new ApiError();
		apiError.setError(ex.getClass().getSimpleName());
		apiError.setErrorMessage(ex.getMessage());
		apiError.setPath(httpRequest.getRequestURI());
		return new ResponseEntity<ApiError>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
