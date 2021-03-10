package com.example.clickandcollectapi.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
        
    @ExceptionHandler(value = RessourceIntrouvableException.class)
    public ResponseEntity<Object> RessourceNotFoundExceptionHandler(RessourceIntrouvableException e) {
        String[] params = e.getMessage().split(";");
        Map<String, Object> obj = new HashMap<>();
        obj.put("timestamp", new Date().toString());
        obj.put("message", "The ressource with id "+params[0]+" was not found");
        obj.put("help",params[1]);
        obj.put("httpStatus", HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(obj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RessourceBadRequestException.class)
    public ResponseEntity<Object> RessourceBadRequestExceptionHandler(RessourceBadRequestException e) {
        String[] params = e.getMessage().split(";");
        Map<String, Object> obj = new HashMap<>();
        obj.put("timestamp", new Date().toString());
        obj.put("message", "The request with id "+params[0]+" was a bad request");
        obj.put("help",params[1]);
        obj.put("httpStatus", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(obj, HttpStatus.BAD_REQUEST);
    }
}
