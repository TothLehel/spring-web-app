package com.spring.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.spring.entity.User;
import com.spring.service.UserService;
import com.spring.validation.UserValidation;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@SessionScope
@Slf4j
public class ModifyController extends ListController{
    
	@Autowired
    private UserService userService;

    @Autowired
    private UserValidation userValidation;

    @Getter @Setter
    private List<User> users;

    @Getter @Setter
    private String originalUsername;
    
    @PostConstruct
    public void listUsers(){
        users = userService.findAll();
        user = new User();
        
    }
    
    public void modifyUser(){
    	log.debug("user is null?: {} ", user == null);
        List<User> userList = userService.findAll();
        //System.out.println(originalUsername);
        if(userService.exists(originalUsername)) {
            userList.remove(userService.getUserById(originalUsername));
            userValidation.saveError(user, userService, userList, !user.getUsername().equals(originalUsername));
            userService.deleteById(originalUsername);
            userService.save(user);
            user = new User();
        }else {
            FacesContext.getCurrentInstance()
                    .addMessage("save",new FacesMessage(FacesMessage.SEVERITY_ERROR,"The User that you want to edit dose not exists!",null));
        }
    }
    @PreDestroy
    private void destroy(){
    	log.debug("destroy called on ModifyController");
    }

}
