package com.gometro.service;

import com.gometro.entity.User;

import java.util.List;

public interface UserService {
    User create(User user);
    List<User> getAll();
    User getById(String id);
    User update(String id, User userDetails);
    User patch(String id, User userDetails);
    void delete(String id);
}
