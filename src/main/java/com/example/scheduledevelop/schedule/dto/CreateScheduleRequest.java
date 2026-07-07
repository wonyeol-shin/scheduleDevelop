package com.example.scheduledevelop.schedule.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    @NotBlank(message = "제목, 내용은 필수값이며 공백은 사용 불가능합니다.")
    @Max(value = 50, message = "일정제목은 최대 50글자까지 가능합니다.")
    private String scheduleName;
    @NotBlank(message = "제목, 내용은 필수값이며 공백은 사용 불가능합니다.")
    @Max(value = 512, message = "일정내용은 최대 512글자까지 가능합니다.")
    private String content;
}
