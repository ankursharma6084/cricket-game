package com.CricketGame.CricketGame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidException(MethodArgumentNotValidException exception) {
        Map<String,String> errorDetails = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->{
            errorDetails.put(error.getField(), error.getDefaultMessage());
        });
        return errorDetails;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidDetailsException.class)
    public Map<String,String> teamNameNotFoundException(InvalidDetailsException exception) {
        Map<String,String> errorDetails = new HashMap<>();
        errorDetails.put("errorMessage" , exception.getMessage());
        return errorDetails;
    }

}
