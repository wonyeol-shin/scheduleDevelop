package com.example.scheduledevelop.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    @NotBlank(message = "이름, 이메일, 패스워드는 필수값이며 공백은 사용 불가능합니다.")
    @Size(min = 2, max = 20, message = "사용자 이름은 2~20 글자까지 입력 가능합니다.")
    private String userName;
    @NotBlank(message = "이름, 이메일, 패스워드는 필수값이며 공백은 사용 불가능합니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    @Max(value = 50, message = "이메일은 최대 50 글자까지 입력 가능합니다.")
    private String email;
    @NotBlank(message = "이름, 이메일, 패스워드는 필수값이며 공백은 사용 불가능합니다.")
    @Max(value = 50, message = "패스워드는 최대 50 글자까지 입력 가능합니다.")
    private String password;
}
