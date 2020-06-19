package com.spring.service;

import com.spring.entity.User;
import com.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
        FacesContext.getCurrentInstance()
                .addMessage("save",new FacesMessage(FacesMessage.SEVERITY_INFO,"User Saved!",null));
    }

    @Override
    public User getUserById(String username) {
        Optional<User> usr = userRepository.findById(username);
        return usr.orElse(null);
    }

    @Override
    public void deleteById(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public boolean exists(String username){
        return userRepository.existsById(username);
    }
}
