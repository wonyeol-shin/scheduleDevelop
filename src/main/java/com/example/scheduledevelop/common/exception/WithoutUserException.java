package com.example.scheduledevelop.common.exception;

import org.springframework.http.HttpStatus;

public class WithoutUserException extends ServiceException {
    // 유저를 찾지 못함
    public WithoutUserException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
