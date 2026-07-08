package com.example.scheduledevelop.schedule.repository;

import com.example.scheduledevelop.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    //List<Schedule> findScheduleById(Long id);
}
