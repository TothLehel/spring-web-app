package com.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.spring.entity.User;
import com.spring.service.UserService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@RequestScope
@Slf4j
public class ListController {

	@Autowired
    private UserService userService;

	@Getter @Setter 
	public User user;
	
	@Getter @Setter
    private List<User> users;

	
    @PostConstruct
    public void listUsers(){
        users = userService.findAll();
        user = new User();
        
    }
    public void modifyUser(User tempUser){
    	user = tempUser;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("modify.xhtml?i=2");
            log.debug("setting tempUser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(User userToDelete){
        userService.deleteById(userToDelete.getUsername());
        log.debug("usertoDelete {} {} {}", userToDelete.getUsername() == user.getUsername(), userToDelete.getUsername(), user.getUsername());
        if(userToDelete.getUsername() == user.getUsername()){
        	user = new User();
        }
        users = userService.findAll();
    }
    @PreDestroy
    private void destroy(){
    	log.debug("destroy called on ListController");
    }
}
