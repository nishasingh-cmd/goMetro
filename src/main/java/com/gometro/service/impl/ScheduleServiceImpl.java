package com.gometro.service.impl;

import com.gometro.entity.Schedule;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.ScheduleRepository;
import com.gometro.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", id));
    }

    @Override
    public Schedule update(Long id, Schedule scheduleDetails) {
        Schedule existingSchedule = getById(id);
        if (scheduleDetails.getDepartureTime() != null) existingSchedule.setDepartureTime(scheduleDetails.getDepartureTime());
        if (scheduleDetails.getArrivalTime() != null) existingSchedule.setArrivalTime(scheduleDetails.getArrivalTime());
        return scheduleRepository.save(existingSchedule);
    }

    @Override
    public void delete(Long id) {
        Schedule schedule = getById(id);
        scheduleRepository.delete(schedule);
    }
}
