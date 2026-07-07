package com.example.scheduledevelop.user.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginUserRequest {
    @NotBlank(message = "이메일, 패스워드는 필수값입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    @Max(value = 50, message = "이메일은 최대 50 글자까지 입력 가능합니다.")
    private String email;
    @NotBlank(message = "이메일, 패스워드는 필수값입니다.")
    @Max(value = 50, message = "패스워드는 최대 50 글자까지 입력 가능합니다.")
    private String password;
}
