package com.demo.smartjob.exceptions;

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
    public ErrorDetails resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        System.out.println("Catchinc ResourceNotFoundException");
        return new ErrorDetails(KEY_MESSAGE, ex.getMessage());
    }

}
