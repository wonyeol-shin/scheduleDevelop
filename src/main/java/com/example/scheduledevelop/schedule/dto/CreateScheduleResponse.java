package com.example.scheduledevelop.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateScheduleResponse {
    private final Long id;
    private final String userName;
    private final String scheduleName;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
}
