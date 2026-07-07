package com.example.scheduledevelop.schedule.service;

import com.example.scheduledevelop.common.exception.IdIsDifferentException;
import com.example.scheduledevelop.common.exception.WithoutScheduleException;
import com.example.scheduledevelop.common.exception.WithoutUserException;
import com.example.scheduledevelop.schedule.dto.*;
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
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(
            CreateScheduleRequest request, Long id)
    {
        User user = findUser(id);
        Schedule savedSchedule = scheduleRepository.save(new Schedule(request.getScheduleName(), request.getContent(), user));
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                user.getUserName(),
                savedSchedule.getScheduleName(),
                savedSchedule.getContent(),
                savedSchedule.getCreateDate(),
                savedSchedule.getModifiedDate()
        );
    }

    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> getAllSchedule() {
       return scheduleRepository.findAll().stream()
                .map((schedule -> new GetAllScheduleResponse(
                        schedule.getId(),
                        schedule.getUser().getUserName(),
                        schedule.getScheduleName(),
                        schedule.getModifiedDate()
                ))).toList();
    }

    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOneSchedule(Long scheduleId) {
        Schedule schedule = findSchedule(scheduleId);
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getUser().getUserName(),
                schedule.getScheduleName(),
                schedule.getContent(),
                schedule.getCreateDate(),
                schedule.getModifiedDate()
        );
    }

    @Transactional
    public void updateSchedule(Long scheduleId, UpdateScheduleRequest request, Long userId) {
        Schedule schedule = findSchedule(scheduleId);
        // 로그인한 user ID와 일정에 저장된 user ID가 일치하는지 한번 더 검증
        if (!(Objects.equals(schedule.getUser().getId(), userId)) ){
            throw new IdIsDifferentException("수정이 불가능한 일정");
        }

        schedule.updateSchedule(request.getScheduleName(), request.getContent());
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        findSchedule(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new WithoutScheduleException("없는 일정입니다.")
        );
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new WithoutUserException("인증값이 유효하지 않습니다. 다시 로그인 바랍니다.")
        );
    }


}
