package com.gometro.controller;

import com.gometro.entity.Train;
import com.gometro.response.ApiResponse;
import com.gometro.service.TrainService;
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
@RequestMapping("/api/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Train>> createTrain(@Valid @RequestBody Train train) {
        Train created = trainService.create(train);
        return new ResponseEntity<>(ApiResponse.success("Train created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Train>>> getAllTrains() {
        List<Train> trains = trainService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Trains retrieved successfully", trains));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Train>> getTrainById(@PathVariable Long id) {
        Train train = trainService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Train retrieved successfully", train));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Train>> updateTrain(@PathVariable Long id, @RequestBody Train train) {
        Train updated = trainService.update(id, train);
        return ResponseEntity.ok(ApiResponse.success("Train updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTrain(@PathVariable Long id) {
        trainService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Train deleted successfully", null));
    }
}
