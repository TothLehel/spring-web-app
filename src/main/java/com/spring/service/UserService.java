package com.spring.service;

import com.spring.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface UserService {

    public List<User> findAll();

    public void save(User user);

    public User getUserById(String username);

    public void deleteById(String username);

    public boolean exists(String username);
}
