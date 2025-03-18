package com.capgemini.lenscart.Exception;

public class LensNotFoundException extends RuntimeException {
    private String message;

    public LensNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
