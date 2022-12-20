package com.taxireview.api.exceptions;

public class DriverNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 1;

    public DriverNotFoundException(String message) {
        super(message);
    }

}
