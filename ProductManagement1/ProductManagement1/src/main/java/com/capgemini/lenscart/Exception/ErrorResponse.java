package com.capgemini.lenscart.Exception;

public class ErrorResponse {
	private String errorCode;
    private String message;
 
    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
 
    public String getErrorCode() {
        return errorCode;
    }
 
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
}
 

//public class ErrorResponse {
//    private int status;
//    private String message;
//    private Map<String, String> validationErrors;
//
//    // Constructor for generic errors
//    public ErrorResponse(int status, String message) {
//        this.status = status;
//        this.message = message;
//    }
//
//    // Constructor for validation errors
//    public ErrorResponse(int status, String message, Map<String, String> validationErrors) {
//        this.status = status;
//        this.message = message;
//        this.validationErrors = validationErrors;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Map<String, String> getValidationErrors() {
//        return validationErrors;
//    }
//
//    public void setValidationErrors(Map<String, String> validationErrors) {
//        this.validationErrors = validationErrors;
//    }
//}
