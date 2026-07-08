package com.example.scheduledevelop.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetOneCommentResponse {
    private final String scheduleAuthor;
    private final String scheduleName;
    private final String content;
    private final String commenters;
    private final String comment;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
}
