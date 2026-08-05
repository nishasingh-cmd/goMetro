package com.gometro.controller;

import com.gometro.entity.Station;
import com.gometro.response.ApiResponse;
import com.gometro.service.StationService;
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
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Station>> createStation(@Valid @RequestBody Station station) {
        Station created = stationService.create(station);
        return new ResponseEntity<>(ApiResponse.success("Station created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Station>>> getAllStations() {
        List<Station> stations = stationService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Stations retrieved successfully", stations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Station>> getStationById(@PathVariable Long id) {
        Station station = stationService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Station retrieved successfully", station));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Station>> updateStation(@PathVariable Long id, @RequestBody Station station) {
        Station updated = stationService.update(id, station);
        return ResponseEntity.ok(ApiResponse.success("Station updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStation(@PathVariable Long id) {
        stationService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Station deleted successfully", null));
    }
}
