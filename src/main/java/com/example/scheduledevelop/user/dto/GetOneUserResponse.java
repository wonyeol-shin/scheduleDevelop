package com.example.scheduledevelop.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetOneUserResponse {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
}
