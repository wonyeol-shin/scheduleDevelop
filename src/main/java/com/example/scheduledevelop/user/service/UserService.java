package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.dto.login.LoginUserForSession;
import com.example.scheduledevelop.user.dto.login.LoginUserRequest;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        User savedUser = userRepository.save( new User(
                request.getUserName(), request.getEmail(), request.getPassword()));

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
       User user = userRepository.findByEmailAndPassword(
               request.getEmail(), request.getPassword()).orElseThrow(
               () -> new IllegalStateException("없는 유저")
       );
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
                () -> new IllegalStateException("없는 유저")
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
                () -> new IllegalStateException("없는 유저")
        );

        user.updateUser(request.getUserName(), request.getEmail(), request.getPassword());
    }

    @Transactional
    public void deleteUser(Long userId) {
        boolean existence = userRepository.existsById(userId);

        if (!existence){
            throw new IllegalStateException("없는 유저");
        }

        userRepository.deleteById(userId);
    }
}
