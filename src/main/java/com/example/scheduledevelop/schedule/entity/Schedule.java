package com.example.scheduledevelop.schedule.entity;

import com.example.scheduledevelop.common.entity.BaseEntity;
import com.example.scheduledevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scheduleName;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(String scheduleName, String content, User user) {
        this.scheduleName = scheduleName;
        this.content = content;
        this.user = user;
    }

    public void updateSchedule(String scheduleName, String content) {
        this.scheduleName = scheduleName;
        this.content = content;
    }

}
