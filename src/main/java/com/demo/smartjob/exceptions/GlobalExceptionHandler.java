package com.demo.smartjob.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final String KEY_MESSAGE = "mensaje";

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails resourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorDetails(KEY_MESSAGE, ex.getMessage());
    }

    //TODO Pending to manage constraint violation exception for email
    @ExceptionHandler(value = {})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails constraintViolationException(ConstraintViolationException ex) {
        StringBuilder errorBuffer = new StringBuilder("Error in these constraints: ");
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            errorBuffer.append(violation.getMessage());
        }
        return new ErrorDetails(KEY_MESSAGE, errorBuffer.toString());
    }
}
