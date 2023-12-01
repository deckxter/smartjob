package com.demo.smartjob.exceptions;

public class BadEmailException extends RuntimeException{
    public BadEmailException(final String message) {
        super(message);
    }
}
