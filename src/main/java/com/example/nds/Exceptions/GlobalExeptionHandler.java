package com.example.nds.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.nds.Constants.HttpStatus;
import com.example.nds.Payload.ApiResponse;

@RestControllerAdvice
public class GlobalExeptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourecNotFoundExceptionHandler(ResourceNotFoundException ex) {
		HttpStatus http = HttpStatus.NOT_FOUND;
		String message = ex.getMessage();
		ApiResponse errorResponse = new ApiResponse(http.getCode(), message, null, null);
		return ResponseEntity.status(http.getCode()).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
	    Map<String, String> resp = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String message = error.getDefaultMessage();
	        resp.put(fieldName, message);
	    });
	    return ResponseEntity.status(status.getCode()).body(resp);
	}

}
