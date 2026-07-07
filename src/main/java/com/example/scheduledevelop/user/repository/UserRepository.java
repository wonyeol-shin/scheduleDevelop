package com.example.scheduledevelop.user.repository;

import com.example.scheduledevelop.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User>findByEmail(@NotBlank(message = "이메일, 패스워드는 필수값입니다.") @Email(message = "이메일 형식이 아닙니다.") @Size(min = 3, max = 50, message = "이메일은 최대 50 글자까지 입력 가능합니다.") String email);
}
