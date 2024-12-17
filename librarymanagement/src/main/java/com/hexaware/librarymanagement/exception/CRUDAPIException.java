package com.hexaware.librarymanagement.exception;

import org.springframework.http.HttpStatus;

public class CRUDAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public CRUDAPIException(HttpStatus status, String message) {
        super(message);  // Call the superclass constructor with the message
        this.status = status;
        this.message = message;  // Store the message
    }

    public CRUDAPIException(HttpStatus status, String message, String detailedMessage) {
        super(message);  // Pass the message to the RuntimeException constructor
        this.status = status;
        this.message = detailedMessage;  // Store the detailed message
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
