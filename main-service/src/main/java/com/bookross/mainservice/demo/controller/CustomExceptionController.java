package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.exception.ExceptionResponse;
import com.bookross.mainservice.demo.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class CustomExceptionController {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ExceptionResponse> handleUserException(UserException exception, HttpServletRequest request) {
        final HttpStatus status = HttpStatus.UNAUTHORIZED;
        ExceptionResponse exceptionResponse;
        exceptionResponse = exception.isShow() ?
                ExceptionResponse.of(status, exception, request) : ExceptionResponse.of(status, new UserException(""), request);
        return new ResponseEntity(exceptionResponse, status);
    }

}