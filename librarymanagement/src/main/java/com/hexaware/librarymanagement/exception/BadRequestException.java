package com.hexaware.librarymanagement.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BadRequestException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    public BadRequestException(HttpStatus status, String message, String message2) {
        super(message);
        this.status = status;
        this.message = message2;
    }

    public BadRequestException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
        this.status = HttpStatus.BAD_REQUEST; // Default to BAD_REQUEST
        this.message = message;
    }

    public BadRequestException() {
        super();
        // TODO Auto-generated constructor stub
    }
    public HttpStatus getstatus() {
        return status;
    }
    public void setstatus(HttpStatus status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
