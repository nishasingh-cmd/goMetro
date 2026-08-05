package com.gometro.controller;

import com.gometro.entity.Schedule;
import com.gometro.response.ApiResponse;
import com.gometro.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Schedule>> createSchedule(@Valid @RequestBody Schedule schedule) {
        Schedule created = scheduleService.create(schedule);
        return new ResponseEntity<>(ApiResponse.success("Schedule created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Schedule>>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Schedules retrieved successfully", schedules));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Schedule>> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Schedule retrieved successfully", schedule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Schedule>> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        Schedule updated = scheduleService.update(id, schedule);
        return ResponseEntity.ok(ApiResponse.success("Schedule updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Schedule deleted successfully", null));
    }
}
