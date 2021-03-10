package com.example.clickandcollectapi.exceptions;

public class RessourceBadRequestException extends RuntimeException{

     /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RessourceBadRequestException(String s) {
        super(s);
    }
}
