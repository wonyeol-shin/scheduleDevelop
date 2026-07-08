package com.example.scheduledevelop.common.exception;

import org.springframework.http.HttpStatus;

public class WithoutCommentException extends ServiceException {
    public WithoutCommentException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
