package com.example.scheduledevelop.user.controller;

import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.dto.login.LoginUserForSession;
import com.example.scheduledevelop.user.dto.login.LoginUserRequest;
import com.example.scheduledevelop.user.dto.login.UserSession;
import com.example.scheduledevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> signup(
           @Valid @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginUserRequest request,
            HttpSession session
    ) {
        LoginUserForSession user = userService.userLogin(request);
        UserSession userSession = new UserSession(user.getId(),user.getUserName());
        session.setAttribute("userLogin", userSession);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "userLogin", required = false) UserSession userSession,
            HttpSession session
    ) {
        if (userSession == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 유저 조회(다건)
    @GetMapping("/users")
    public ResponseEntity<List<GetAllUserResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    // 유저 조회(단건)
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetOneUserResponse> getOne(
            @PathVariable Long userId
    ) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getOneUser(userId));
    }

    // 유저 수정
    @PostMapping("/users")
    public ResponseEntity<Void> update(
            @Valid @RequestBody UpdateUserRequest request,
            @SessionAttribute(name = "userLogin", required = false) UserSession userSession,
            HttpSession session
    ) {

        if (userSession == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        session.invalidate();
        userService.updateUser(request, userSession.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 유저 삭제
    @DeleteMapping("/users")
    public ResponseEntity<Void> delete(
            @SessionAttribute(name = "userLogin", required = false) UserSession userSession,
            HttpSession session
    ) {

        if (userSession == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        session.invalidate();
        userService.deleteUser(userSession.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
