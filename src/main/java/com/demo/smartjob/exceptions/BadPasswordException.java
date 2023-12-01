package com.demo.smartjob.exceptions;

public class BadPasswordException extends RuntimeException{
    public BadPasswordException(final String message) {
        super(message);
    }
}
