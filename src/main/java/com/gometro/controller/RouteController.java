package com.gometro.controller;

import com.gometro.entity.Route;
import com.gometro.response.ApiResponse;
import com.gometro.service.RouteService;
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
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Route>> createRoute(@Valid @RequestBody Route route) {
        Route created = routeService.create(route);
        return new ResponseEntity<>(ApiResponse.success("Route created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Route>>> getAllRoutes() {
        List<Route> routes = routeService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Routes retrieved successfully", routes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Route>> getRouteById(@PathVariable Long id) {
        Route route = routeService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Route retrieved successfully", route));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Route>> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        Route updated = routeService.update(id, route);
        return ResponseEntity.ok(ApiResponse.success("Route updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRoute(@PathVariable Long id) {
        routeService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Route deleted successfully", null));
    }
}
