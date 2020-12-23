package com.asavin.pilsnerbar.service;

import com.asavin.pilsnerbar.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> findAll();
    User findById(Long id);
    User getAuthorizedUser();
}
