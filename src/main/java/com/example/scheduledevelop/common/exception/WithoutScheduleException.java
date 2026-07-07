package com.example.scheduledevelop.common.exception;

import org.springframework.http.HttpStatus;

public class WithoutScheduleException extends ServiceException {
    public WithoutScheduleException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
