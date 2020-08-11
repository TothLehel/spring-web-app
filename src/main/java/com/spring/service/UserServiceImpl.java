package com.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.User;
import com.spring.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    public User getUserById(String username) {
        Optional<User> usr = userRepository.findById(username);
        return usr.orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(String username) {
        userRepository.deleteById(username);
    }

    @Override
    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    public boolean exists(String username){
        return userRepository.existsById(username);
    }
}
