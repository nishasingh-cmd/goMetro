package com.gometro.controller;

import com.gometro.entity.User;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for User CRUD operations")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @Operation(summary = "Create a user")
    @Transactional
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("Role is required");
        }
        String prefix = switch (user.getRole()) {
            case PASSENGER -> "PASS";
            case ADMIN -> "ADMIN";
            case TICKET_AGENT, MAINTENANCE_STAFF -> "EMP";
        };
        user.setUserId(prefix + "-" + UUID.randomUUID().toString());
        if (user.getIsActive() == null) {
            user.setIsActive(true);
        }
        User createdUser = userRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retrieve all users")
    @Transactional(readOnly = true)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a user by ID")
    @Transactional(readOnly = true)
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user")
    @Transactional
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if (userDetails.getFirstName() != null) existingUser.setFirstName(userDetails.getFirstName());
        if (userDetails.getLastName() != null) existingUser.setLastName(userDetails.getLastName());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) existingUser.setPassword(userDetails.getPassword());
        if (userDetails.getContact() != null) existingUser.setContact(userDetails.getContact());
        if (userDetails.getRole() != null) existingUser.setRole(userDetails.getRole());
        if (userDetails.getIsActive() != null) existingUser.setIsActive(userDetails.getIsActive());
        if (userDetails.getHireDate() != null) existingUser.setHireDate(userDetails.getHireDate());
        if (userDetails.getStationId() != null) existingUser.setStationId(userDetails.getStationId());
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a user")
    @Transactional
    public ResponseEntity<User> patchUser(@PathVariable String id, @RequestBody User userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if (userDetails.getFirstName() != null) existingUser.setFirstName(userDetails.getFirstName());
        if (userDetails.getLastName() != null) existingUser.setLastName(userDetails.getLastName());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) existingUser.setPassword(userDetails.getPassword());
        if (userDetails.getContact() != null) existingUser.setContact(userDetails.getContact());
        if (userDetails.getRole() != null) existingUser.setRole(userDetails.getRole());
        if (userDetails.getIsActive() != null) existingUser.setIsActive(userDetails.getIsActive());
        if (userDetails.getHireDate() != null) existingUser.setHireDate(userDetails.getHireDate());
        if (userDetails.getStationId() != null) existingUser.setStationId(userDetails.getStationId());
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}
