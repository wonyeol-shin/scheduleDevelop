package com.example.scheduledevelop.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PageableGetScheduleResponse {
    private final Long scheduleId;
    private final String scheduleName;
    private final String scheduleContent;
    private final Long commentCount;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
}
