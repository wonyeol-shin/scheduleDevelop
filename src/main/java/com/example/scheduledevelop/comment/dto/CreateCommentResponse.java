package com.example.scheduledevelop.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateCommentResponse {
    private final Long id;
    private final String comment;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
}
