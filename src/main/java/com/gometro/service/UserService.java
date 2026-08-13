package com.gometro.service;

import com.gometro.dto.UserResponseDTO;
import com.gometro.entity.User;
import java.util.List;

public interface UserService {
    User create(User user);
    List<User> getAll();
    User getById(Long id);
    UserResponseDTO getResponseById(Long id);
    List<UserResponseDTO> getAllResponses();
    User update(Long id, User userDetails);
    void delete(Long id);
}
