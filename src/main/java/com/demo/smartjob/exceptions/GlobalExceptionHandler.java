package com.demo.smartjob.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final String KEY_MESSAGE = "mensaje";

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails resourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorDetails(KEY_MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails emailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ErrorDetails(KEY_MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(value = {BadPasswordException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails badPasswordException(BadPasswordException ex) {
        return new ErrorDetails(KEY_MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(value = {BadEmailException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails badEmailException(BadEmailException ex) {
        return new ErrorDetails(KEY_MESSAGE, ex.getMessage());
    }
}
