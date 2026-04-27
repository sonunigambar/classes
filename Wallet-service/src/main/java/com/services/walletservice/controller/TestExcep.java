package com.services.walletservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TestExcep {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ArithmeticException ex) {

        ErrorResponse error = new ErrorResponse(

                ex.getMessage(),

                HttpStatus.INTERNAL_SERVER_ERROR.value()

        );

        return new ResponseEntity<>(error, HttpStatus.OK);

    }
}
