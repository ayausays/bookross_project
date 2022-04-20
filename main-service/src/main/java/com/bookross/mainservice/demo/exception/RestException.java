package com.bookross.mainservice.demo.exception;


import lombok.Getter;

import java.util.Optional;

@Getter
public abstract class RestException extends RuntimeException {
    private static final String USER_DEFAULT_MESSAGE = "Ошибка на стороне сервера, обратитесь Администратору";
    private static final String MESSAGE_CODE = "admin.message.server.error";

    private String messageCode;
    private String sysMessage;

    protected RestException() {
    }

    protected RestException(String sysMessage) {
        super(USER_DEFAULT_MESSAGE);
        this.messageCode = MESSAGE_CODE;
        this.sysMessage = sysMessage;
    }

    protected RestException(Throwable throwable) {
        super(throwable);
        this.messageCode = MESSAGE_CODE;
        this.sysMessage = USER_DEFAULT_MESSAGE;
    }

    protected RestException(String messageCode, String sysMessage) {
        super(sysMessage);
        this.messageCode = Optional.ofNullable(messageCode).orElse(MESSAGE_CODE);
        this.sysMessage = Optional.ofNullable(sysMessage).orElse(USER_DEFAULT_MESSAGE);
    }

    protected RestException(String messageCode, String message, String sysMessage) {
        super(Optional.ofNullable(message).orElse(USER_DEFAULT_MESSAGE));
        this.messageCode = Optional.ofNullable(messageCode).orElse(MESSAGE_CODE);
        this.sysMessage = Optional.ofNullable(sysMessage).orElse(USER_DEFAULT_MESSAGE);
    }

    protected RestException(String messageCode, String message, Throwable throwable) {
        super(Optional.ofNullable(message).orElse(USER_DEFAULT_MESSAGE), throwable);
        this.messageCode = Optional.ofNullable(messageCode).orElse(MESSAGE_CODE);
        this.sysMessage = USER_DEFAULT_MESSAGE;
    }

    protected RestException(String messageCode, String message, String sysMessage, Throwable throwable) {
        super(Optional.ofNullable(message).orElse(USER_DEFAULT_MESSAGE), throwable);
        this.messageCode = Optional.ofNullable(messageCode).orElse(MESSAGE_CODE);
        this.sysMessage = Optional.ofNullable(sysMessage).orElse(USER_DEFAULT_MESSAGE);
    }
}