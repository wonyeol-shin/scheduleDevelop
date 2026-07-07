package com.example.scheduledevelop.common.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // bean validation 관련 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> methodInvalidException(
        MethodArgumentNotValidException ex
    ) {
        List<String> message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map((DefaultMessageSourceResolvable::getDefaultMessage))
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    // user, schedule 관련 처리
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> serviceException(
            ServiceException ex
    ) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

}
