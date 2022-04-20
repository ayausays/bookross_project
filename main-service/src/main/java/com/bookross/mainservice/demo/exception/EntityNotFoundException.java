package com.bookross.mainservice.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends RestException{
    private static final String ENTITY_NOT_FOUND = "Entity %s is not found by %s %s";
    private static final String ENTITY_NOT_FOUND_BY_ID = "Entity %s is not found by id: %s";
    private static final String ENTITY_NOT_FOUND_BY_ATTR = "Entity %s is not found by attributes: %s";
    private static final String USER_DEFAULT_MESSAGE = "Ошибка на стороне сервера, обратитесь Администратору";
    private static final String MESSAGE_CODE = "admin.message.server.error";

    public EntityNotFoundException(Class entityClass, String attribute, Object value) {
        super(MESSAGE_CODE, String.format(ENTITY_NOT_FOUND, entityClass.getSimpleName(), attribute, value), USER_DEFAULT_MESSAGE);
    }

    public EntityNotFoundException(Class entityClass, Object id) {
        super(MESSAGE_CODE, String.format(ENTITY_NOT_FOUND_BY_ID, entityClass.getSimpleName(), id), USER_DEFAULT_MESSAGE);
    }

    public EntityNotFoundException(Class entityClass, Object[] attributes) {
        super(MESSAGE_CODE, String.format(ENTITY_NOT_FOUND_BY_ATTR, entityClass.getSimpleName(), attributes), USER_DEFAULT_MESSAGE);
    }
}
