package com.spring.service;

import java.util.List;

import com.spring.entity.User;

public interface UserService {

    public List<User> findAll();

    public void save(User user);

    public User getUserById(String username);

    public void deleteById(String username);

    public boolean exists(String username);
}
