package com.example.scheduledevelop.comment.controller;

import com.example.scheduledevelop.comment.dto.*;
import com.example.scheduledevelop.comment.service.CommentService;

import com.example.scheduledevelop.user.dto.login.UserSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    // 댓글 생성
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> create(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequest request,
            @SessionAttribute(name = "userLogin", required = false) UserSession userSession

    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.createComment(scheduleId, userSession.getId(), request));
    }

    // 댓글 조회(다건)
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetAllCommentResponse>> getAll(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllComment(scheduleId));
    }

    //댓글 조회(단건)
    @GetMapping("/schedules/comments/{commentId}")
    public ResponseEntity<GetOneCommentResponse> getOne(
        @PathVariable Long commentId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getOneComment(commentId));
    }

    // 댓글 수정
    @PatchMapping("/schedules/comments/{commentId}")
    public ResponseEntity<Void> update(
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentRequest request,
            @SessionAttribute(name = "userLogin", required = false) UserSession userSession
            ) {
        commentService.updateComment(commentId, request, userSession.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 댓글 삭제
    @DeleteMapping("/schedules/comments/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long commentId,
            @SessionAttribute(name = "userLogin", required = false) UserSession userSession
    ) {
        commentService.deleteComment(commentId, userSession.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
