package com.example.scheduledevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SchdeduledevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchdeduledevelopApplication.class, args);
    }

}
