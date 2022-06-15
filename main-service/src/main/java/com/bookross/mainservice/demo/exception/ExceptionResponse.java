package com.bookross.mainservice.demo.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Data
public class ExceptionResponse {
    private final LocalDateTime timestamp;
    private final String status;
    private final String error;
    private final String message;
    private final String path;


    private ExceptionResponse(LocalDateTime timestamp, String status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    private static String getExceptionMessage(Throwable t){
        return Optional.ofNullable(t.getMessage()).orElse(t.toString());
    }


    public static ExceptionResponse of(String status, String error, String message, String path){
        return new ExceptionResponse(LocalDateTime.now(), status, error, message, path);
    }

    public static ExceptionResponse of(HttpStatus status, Exception ex, HttpServletRequest request){
        Optional<Throwable> rootCause = Stream.iterate(ex, Throwable::getCause).filter(element -> element.getCause() == null).findFirst();
        String message = rootCause.isPresent()? getExceptionMessage(rootCause.get()) : getExceptionMessage(ex);
        return of(Integer.toString(status.value()), status.getReasonPhrase(), message, request.getRequestURI());
    }

    public static ExceptionResponse of(HttpStatus status, String error, Exception ex, HttpServletRequest request){
        return of(Integer.toString(status.value()), error, ex.getMessage(), request.getRequestURI());
    }
}