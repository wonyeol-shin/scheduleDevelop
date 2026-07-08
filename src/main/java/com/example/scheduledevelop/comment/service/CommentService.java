package com.example.scheduledevelop.comment.service;

import com.example.scheduledevelop.comment.dto.*;
import com.example.scheduledevelop.comment.entity.Comment;
import com.example.scheduledevelop.comment.repository.CommentRepository;
import com.example.scheduledevelop.common.exception.IdIsDifferentException;
import com.example.scheduledevelop.common.exception.WithoutCommentException;
import com.example.scheduledevelop.common.exception.WithoutUserException;
import com.example.scheduledevelop.schedule.entity.Schedule;
import com.example.scheduledevelop.schedule.repository.ScheduleRepository;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new WithoutUserException("로그인 정보가 유효하지 않습니다.")
        );
    }

    private Schedule findSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new WithoutUserException("존재하지 않는 일정입니다.")
        );
    }

    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new WithoutCommentException("존재하지 않는 댓글입니다")
        );
    }

    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, Long userId ,CreateCommentRequest request) {
        User user =  findUser(userId);
        Schedule schedule = findSchedule(scheduleId);

        Comment savedComment = commentRepository.
                save(new Comment(request.getComment(), schedule, user));

        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getComment(),
                savedComment.getCreateDate(),
                savedComment.getModifiedDate()
        );
    }

    @Transactional(readOnly = true)
    public List<GetAllCommentResponse> getAllComment(Long scheduleId) {
        Schedule schedule = findSchedule(scheduleId);

        return commentRepository.findByScheduleId(schedule.getId()).stream()
                .map(
                        (comment -> new GetAllCommentResponse(
                                comment.getId(),
                                comment.getComment(),
                                comment.getUser().getUserName(),
                                comment.getModifiedDate()
                        ))
                ).toList();
    }

    @Transactional(readOnly = true)
    public GetOneCommentResponse getOneComment(Long commentId) {
        Comment comment = findComment(commentId);
        return new GetOneCommentResponse(
                comment.getSchedule().getUser().getUserName(),
                comment.getSchedule().getScheduleName(),
                comment.getSchedule().getContent(),
                comment.getUser().getUserName(),
                comment.getComment(),
                comment.getCreateDate(),
                comment.getModifiedDate()
        );
    }

    @Transactional // 수정하려는 댓글 id , 수정 dto, 현재 로그인 된 사용자 id
    public void updateComment(Long commentId, UpdateCommentRequest request, Long id) {
        // 존재하는 댓글인지 먼저 확인
        Comment comment = findComment(commentId);
        // 댓글을 작성한 id와 현재 로그인한 사용자 id가 다를경우
        if (!Objects.equals(comment.getUser().getId(), id)){
            throw new IdIsDifferentException("댓글을 수정 할 권한이 없습니다.");
        }
        // 회원 정보가 정말 우효한지 확인, 생성도 아니고 수정일때는 검증이 필요없을 거 같음
        //findUser(id);

        comment.updateComment(request.getComment());
    }

    @Transactional
    public void deleteComment(Long commentId, Long id) {
        // 존재하는 댓글인지 먼저 확인
        Comment comment = findComment(commentId);

        if (!Objects.equals(comment.getUser().getId(), id)){
            throw new IdIsDifferentException("댓글을 삭제 할 권한이 없습니다.");
        }

        commentRepository.deleteById(commentId);
    }
}
