package com.example.scheduledevelop.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetAllUserResponse {
    private final String userName;
    private final String email;
}
