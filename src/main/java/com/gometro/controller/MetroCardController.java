package com.gometro.controller;

import com.gometro.entity.MetroCard;
import com.gometro.response.ApiResponse;
import com.gometro.service.MetroCardService;
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
@RequestMapping("/api/metro-cards")
public class MetroCardController {

    private final MetroCardService metroCardService;

    public MetroCardController(MetroCardService metroCardService) {
        this.metroCardService = metroCardService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MetroCard>> createMetroCard(@Valid @RequestBody MetroCard metroCard) {
        MetroCard created = metroCardService.create(metroCard);
        return new ResponseEntity<>(ApiResponse.success("Metro card created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MetroCard>>> getAllMetroCards() {
        List<MetroCard> cards = metroCardService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Metro cards retrieved successfully", cards));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MetroCard>> getMetroCardById(@PathVariable Long id) {
        MetroCard card = metroCardService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Metro card retrieved successfully", card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MetroCard>> updateMetroCard(@PathVariable Long id, @RequestBody MetroCard metroCard) {
        MetroCard updated = metroCardService.update(id, metroCard);
        return ResponseEntity.ok(ApiResponse.success("Metro card updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMetroCard(@PathVariable Long id) {
        metroCardService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Metro card deleted successfully", null));
    }
}
