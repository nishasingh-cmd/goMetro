package com.gometro.controller;

import com.gometro.entity.Fare;
import com.gometro.response.ApiResponse;
import com.gometro.service.FareService;
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
@RequestMapping("/api/fares")
public class FareController {

    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Fare>> createFare(@Valid @RequestBody Fare fare) {
        Fare created = fareService.create(fare);
        return new ResponseEntity<>(ApiResponse.success("Fare created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Fare>>> getAllFares() {
        List<Fare> fares = fareService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Fares retrieved successfully", fares));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Fare>> getFareById(@PathVariable Long id) {
        Fare fare = fareService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Fare retrieved successfully", fare));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Fare>> updateFare(@PathVariable Long id, @RequestBody Fare fare) {
        Fare updated = fareService.update(id, fare);
        return ResponseEntity.ok(ApiResponse.success("Fare updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFare(@PathVariable Long id) {
        fareService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Fare deleted successfully", null));
    }
}
