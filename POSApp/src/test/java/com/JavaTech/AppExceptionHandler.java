package com.JavaTech.ExceptionHandle;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.JavaTech.Dto.ErrorResponse;
import com.JavaTech.Utils.ExceptionMessage;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);

	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	@ExceptionHandler(JDBCConnectionException.class)
	public String handleConnectionError(Exception ex) {
	  LOGGER.error(ex.getMessage(), ex);
	  return ExceptionMessage.CONNECTION_ERROR;
	}
	
	@ExceptionHandler(value = ElementAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleElementAlreadyExistsException(ElementAlreadyExistsException ex) {
	    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
	
	@ExceptionHandler(value = NoSuchElementFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNoSuchElementFoundException(NoSuchElementFoundException ex) {
	    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){		
		Map<String,String>errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(err->{
			errorMap.put(err.getField(),err.getDefaultMessage());
		});
		return errorMap;
	}
		 
}
