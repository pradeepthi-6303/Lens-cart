package com.capgemini.lenscart.Exception;

import java.util.HashMap;

import java.util.Map;
 
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
 
@ControllerAdvice

public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getAllErrors().forEach((error) -> {

	        String fieldName = ((FieldError) error).getField();

	        String errorMessage = error.getDefaultMessage();

	errors.put(fieldName, errorMessage);

	    });

	return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(FrameNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleFrameNotFoundException(FrameNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse("FRAME_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
	@ExceptionHandler(GlassNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleGlassNotFoundException(GlassNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse("GLASS_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
   
	@ExceptionHandler(LensNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleLensNotFoundException(LensNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse("LENS_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

	@ExceptionHandler(SunglassesNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleUserNotFoundException(SunglassesNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse("SUNGLASSES_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
 
    // Handle InvalidUserException

//    @ExceptionHandler(InvalidUserException.class)
//
//    public ResponseEntity<ErrorResponse> handleInvalidUserException(InvalidUserException ex) {
//
//        ErrorResponse errorResponse = new ErrorResponse("INVALID_USER", ex.getMessage());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//
//    }
 
//    // Handle general exceptions

//    @ExceptionHandler(ApiException.class)

//    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {

//        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());

//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

//    }
 
    // Handle validation errors (e.g., @Valid errors)

//    @ExceptionHandler(javax.validation.ConstraintViolationException.class)

//    public ResponseEntity<ErrorResponse> handleValidationException(javax.validation.ConstraintViolationException ex) {

//        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_ERROR", ex.getMessage());

//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

//   }
 
    // Handle all other exceptions

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        ErrorResponse errorResponse = new ErrorResponse("GENERIC_ERROR", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
 
 