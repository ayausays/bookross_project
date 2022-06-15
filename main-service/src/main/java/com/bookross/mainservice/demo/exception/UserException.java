package com.bookross.mainservice.demo.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Getter
public class UserException extends RuntimeException {
    private boolean show = false;

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, boolean show) {
        super(message);
        this.show = show;
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
