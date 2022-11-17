package com.banking.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseStatus(HttpStatus.NOT_FOUND)
//Marks a method or exception class with the status code() and reason() that should be returned.
public class ResourceNotFoundException extends RuntimeException {
    //The Runtime Exception is the parent class in all exceptions of the
    // Java programming language that are expected to crash or break down the program or application when they occur
    private static final long serialVersionUID = 1L;
    //If no match is found, then an InvalidClassException is thrown.
    // Serialization in Java allows us to convert an Object to stream that we
    // can send over the network or save it as file or store in DB for later usage.
    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String message) {
        super(message);
    }
    // get the message from the resource not found exception

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
