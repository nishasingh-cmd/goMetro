package com.gometro.service.impl;

import com.gometro.entity.User;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.UserRepository;
import com.gometro.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User update(Long id, User userDetails) {
        User existingUser = getById(id);
        if (userDetails.getFirstName() != null) existingUser.setFirstName(userDetails.getFirstName());
        if (userDetails.getLastName() != null) existingUser.setLastName(userDetails.getLastName());
        if (userDetails.getEmail() != null) existingUser.setEmail(userDetails.getEmail());
        if (userDetails.getPhoneNumber() != null) existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        if (userDetails.getRole() != null) existingUser.setRole(userDetails.getRole());
        if (userDetails.getActive() != null) existingUser.setActive(userDetails.getActive());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }
}
