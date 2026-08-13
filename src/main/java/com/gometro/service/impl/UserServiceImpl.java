package com.gometro.service.impl;

import com.gometro.dto.UserResponseDTO;
import com.gometro.entity.User;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.mapper.DtoMapper;
import com.gometro.repository.UserRepository;
import com.gometro.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getResponseById(Long id) {
        User user = getById(id);
        return DtoMapper.toUserResponseDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllResponses() {
        return userRepository.findAll().stream()
                .map(DtoMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }
}
