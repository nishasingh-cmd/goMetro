package com.gometro.controller;

import com.gometro.entity.MaintenanceIssue;
import com.gometro.response.ApiResponse;
import com.gometro.service.MaintenanceIssueService;
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
@RequestMapping("/api/maintenance-issues")
public class MaintenanceIssueController {

    private final MaintenanceIssueService maintenanceIssueService;

    public MaintenanceIssueController(MaintenanceIssueService maintenanceIssueService) {
        this.maintenanceIssueService = maintenanceIssueService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MaintenanceIssue>> createMaintenanceIssue(@Valid @RequestBody MaintenanceIssue maintenanceIssue) {
        MaintenanceIssue created = maintenanceIssueService.create(maintenanceIssue);
        return new ResponseEntity<>(ApiResponse.success("Maintenance issue created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MaintenanceIssue>>> getAllMaintenanceIssues() {
        List<MaintenanceIssue> list = maintenanceIssueService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Maintenance issues retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceIssue>> getMaintenanceIssueById(@PathVariable Long id) {
        MaintenanceIssue item = maintenanceIssueService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Maintenance issue retrieved successfully", item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceIssue>> updateMaintenanceIssue(@PathVariable Long id, @RequestBody MaintenanceIssue details) {
        MaintenanceIssue updated = maintenanceIssueService.update(id, details);
        return ResponseEntity.ok(ApiResponse.success("Maintenance issue updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMaintenanceIssue(@PathVariable Long id) {
        maintenanceIssueService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Maintenance issue deleted successfully", null));
    }
}
