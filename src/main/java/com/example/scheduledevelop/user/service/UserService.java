package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.common.exception.LoginFailureException;
import com.example.scheduledevelop.common.exception.WithoutUserException;
import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.dto.login.LoginUserForSession;
import com.example.scheduledevelop.user.dto.login.LoginUserRequest;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        User savedUser = userRepository.save( new User(
                request.getUserName(), request.getEmail(), passwordEncoder.encode(request.getPassword())));
        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreateDate(),
                savedUser.getModifiedDate()
        );
    }

    @Transactional(readOnly = true)
    public LoginUserForSession userLogin(LoginUserRequest request) {
        // 보안상 이메일만 틀렸다고 알려주지 않음
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new LoginFailureException("email 또는 password가 틀렸습니다.")
        );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new LoginFailureException("email 또는 password가 틀렸습니다.");
        }

        return new LoginUserForSession(user.getId(), user.getUserName());
    }

    @Transactional(readOnly = true)
    public List<GetAllUserResponse> getAllUser() {
      return userRepository.findAll().stream()
                .map((user -> new GetAllUserResponse(
                        user.getUserName(), user.getEmail())))
                .toList();
    }

    @Transactional(readOnly = true)
    public GetOneUserResponse getOneUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new WithoutUserException("존재하지 않는 유저입니다.")
        );

        return new GetOneUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateDate(),
                user.getModifiedDate()
        );

    }

    @Transactional
    public void updateUser(UpdateUserRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new WithoutUserException("존재하지 않는 유저입니다.")
        );

        user.updateUser(request.getUserName(), request.getEmail(), request.getPassword());
    }

    @Transactional
    public void deleteUser(Long userId) {
        boolean existence = userRepository.existsById(userId);

        if (!existence){
            throw new WithoutUserException("존재하지 않는 유저입니다.");
        }

        userRepository.deleteById(userId);
    }
}
