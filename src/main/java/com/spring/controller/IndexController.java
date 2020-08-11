package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.spring.entity.User;
import com.spring.service.UserService;
import com.spring.validation.UserValidation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@RequestScope
@NoArgsConstructor
@Slf4j
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidation userValidation;

    @Getter @Setter
    private User user;

    @Getter @Setter
    private List<User> users;

    @PostConstruct
    private void init(){
    	user = new User();
    	users = new ArrayList<>();
    }
    
    
    public void saveUser(){
        List<User> userList = userService.findAll(); //itt queryvel kéne kibányászni azokat az embereket akiknek van telefonszámuk
        user=userValidation.saveError(user,userService,userList,true);
        if(user != null) {
            userService.save(user);
            log.debug("facesContext");
            FacesContext.getCurrentInstance()
            	.addMessage("save",new FacesMessage(FacesMessage.SEVERITY_INFO,"User Saved!",null));
            user = new User();
            users=userList;
        }
    }
}
