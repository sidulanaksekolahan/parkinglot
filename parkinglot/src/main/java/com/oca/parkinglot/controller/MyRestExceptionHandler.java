package com.oca.parkinglot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Global exception
@RestControllerAdvice
public class MyRestExceptionHandler {

    // add exception handler for data not found exception
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(DataNotFoundException e) {

        // create custom response
        CustomErrorResponse errorResponse =
                new CustomErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        System.currentTimeMillis()
                );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // add exception handler to catch any exception
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(Exception e) {

        // create custom response
        CustomErrorResponse errorResponse =
                new CustomErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        System.currentTimeMillis()
                );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
