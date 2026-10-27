package com.gometro.service.impl;

import com.gometro.entity.User;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.UserRepository;
import com.gometro.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(User user) {
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
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    @Transactional
    public User update(String id, User userDetails) {
        User existingUser = getById(id);
        if (userDetails.getFirstName() != null) existingUser.setFirstName(userDetails.getFirstName());
        if (userDetails.getLastName() != null) existingUser.setLastName(userDetails.getLastName());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) existingUser.setPassword(userDetails.getPassword());
        if (userDetails.getContact() != null) existingUser.setContact(userDetails.getContact());
        if (userDetails.getRole() != null) existingUser.setRole(userDetails.getRole());
        if (userDetails.getIsActive() != null) existingUser.setIsActive(userDetails.getIsActive());
        if (userDetails.getHireDate() != null) existingUser.setHireDate(userDetails.getHireDate());
        if (userDetails.getStationId() != null) existingUser.setStationId(userDetails.getStationId());
        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public User patch(String id, User userDetails) {
        return update(id, userDetails);
    }

    @Override
    @Transactional
    public void delete(String id) {
        User user = getById(id);
        userRepository.delete(user);
    }
}
