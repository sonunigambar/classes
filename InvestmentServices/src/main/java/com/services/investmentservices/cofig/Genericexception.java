package com.services.investmentservices.cofig;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class Genericexception {

    /*@ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handelTimeoutException(){
        return
    }*/
}
