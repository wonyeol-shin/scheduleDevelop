package com.example.scheduledevelop.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetAllCommentResponse {
    private final Long id;
    private final String comment;
    private final String commenters;
    private final LocalDateTime modifiedDate;
}
