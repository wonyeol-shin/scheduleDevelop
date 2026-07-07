package com.example.scheduledevelop.common.exception;

import org.springframework.http.HttpStatus;

public class IdIsDifferentException extends ServiceException {
    public IdIsDifferentException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
