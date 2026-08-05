package com.gometro.controller;

import com.gometro.entity.RouteStation;
import com.gometro.response.ApiResponse;
import com.gometro.service.RouteStationService;
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
@RequestMapping("/api/route-stations")
public class RouteStationController {

    private final RouteStationService routeStationService;

    public RouteStationController(RouteStationService routeStationService) {
        this.routeStationService = routeStationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RouteStation>> createRouteStation(@Valid @RequestBody RouteStation routeStation) {
        RouteStation created = routeStationService.create(routeStation);
        return new ResponseEntity<>(ApiResponse.success("RouteStation created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RouteStation>>> getAllRouteStations() {
        List<RouteStation> list = routeStationService.getAll();
        return ResponseEntity.ok(ApiResponse.success("RouteStations retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RouteStation>> getRouteStationById(@PathVariable Long id) {
        RouteStation item = routeStationService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("RouteStation retrieved successfully", item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RouteStation>> updateRouteStation(@PathVariable Long id, @RequestBody RouteStation details) {
        RouteStation updated = routeStationService.update(id, details);
        return ResponseEntity.ok(ApiResponse.success("RouteStation updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRouteStation(@PathVariable Long id) {
        routeStationService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("RouteStation deleted successfully", null));
    }
}
