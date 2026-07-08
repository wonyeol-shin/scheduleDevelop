package com.example.scheduledevelop.comment.entity;

import com.example.scheduledevelop.common.config.CommonEntity;
import com.example.scheduledevelop.common.entity.BaseEntity;
import com.example.scheduledevelop.schedule.entity.Schedule;
import com.example.scheduledevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(String comment, Schedule schedule, User user) {
        this.comment = comment;
        this.schedule = schedule;
        this.user = user;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }
}
