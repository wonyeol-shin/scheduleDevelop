package com.example.scheduledevelop.user.dto.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginUserForSession {
    private final Long id;
    private final String userName;
}
