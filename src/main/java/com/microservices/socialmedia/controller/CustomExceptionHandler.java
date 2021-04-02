package com.microservices.socialmedia.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microservices.socialmedia.exception.NoContentException;
import com.microservices.socialmedia.exception.UserException;
import com.microservices.socialmedia.model.UserExceptionResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest req){
		
		UserExceptionResponse exceptionResponse=new UserExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@ExceptionHandler(UserException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserException ex,WebRequest req){
		
		UserExceptionResponse exceptionResponse=new UserExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(NoContentException.class)
	public final ResponseEntity<Object> handleNoContentException(NoContentException ex,WebRequest req){
		
		UserExceptionResponse exceptionResponse=new UserExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.NO_CONTENT);
	
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		UserExceptionResponse userExceptionResponse=new UserExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		return new ResponseEntity(userExceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	

	
	

}
