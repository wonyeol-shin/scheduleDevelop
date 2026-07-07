package com.example.scheduledevelop.common.exception;

import org.springframework.http.HttpStatus;

public class LoginFailureException extends ServiceException {
    // 유저를 찾지 못함
    public LoginFailureException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
