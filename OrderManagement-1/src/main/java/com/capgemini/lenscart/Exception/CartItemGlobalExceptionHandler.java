package com.capgemini.lenscart.Exception;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartItemGlobalExceptionHandler {
	@ExceptionHandler
	(CartItemNotFoundException.class)
    public ResponseEntity<String> handleCartItemNotFound(CartNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // You can add more exception handlers for other exceptions here
}
