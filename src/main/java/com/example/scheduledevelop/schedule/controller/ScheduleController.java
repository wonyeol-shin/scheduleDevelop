package com.example.scheduledevelop.schedule.controller;

import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.service.ScheduleService;
import com.example.scheduledevelop.user.dto.login.UserSession;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> create(
            @Valid @RequestBody CreateScheduleRequest request,
            @SessionAttribute(name = "userLogin", required = false) UserSession userSession
    ) {
        if (isInvalidUser(userSession)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

      return ResponseEntity.status(HttpStatus.CREATED)
                .body(scheduleService.createSchedule(request, userSession.getId()));
    }

    // 일정 조회(다건)
    @GetMapping("/schedules")
    public ResponseEntity<List<GetAllScheduleResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedule());
    }

    //일정 조회(단건)
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOne(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOneSchedule(scheduleId));
    }

    // 일정 수정
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> update(
             @PathVariable Long scheduleId,
             @Valid @RequestBody UpdateScheduleRequest request,
             @SessionAttribute(name = "userLogin", required = false) UserSession userSession
    ) {
        if (isInvalidUser(userSession)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        scheduleService.updateSchedule(scheduleId,request ,userSession.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long scheduleId,
            @SessionAttribute(name = "userLogin", required = false) UserSession userSession
    ) {
        if (isInvalidUser(userSession)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private boolean isInvalidUser(UserSession userSession) {
        return  userSession == null;
    }

}
