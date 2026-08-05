package com.gometro.service;

import com.gometro.entity.Schedule;
import java.util.List;

public interface ScheduleService {
    Schedule create(Schedule schedule);
    List<Schedule> getAll();
    Schedule getById(Long id);
    Schedule update(Long id, Schedule scheduleDetails);
    void delete(Long id);
}
