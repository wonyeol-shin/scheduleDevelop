package com.example.scheduledevelop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @NotBlank(message = "제목, 내용은 필수값이며 공백은 사용 불가능합니다.")
    @Size(min = 1, max = 50,message = "댓글은 최대 50글자까지 가능합니다." )
    private String comment;
}
