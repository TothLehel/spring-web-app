package com.spring.controller;

import com.spring.entity.User;
import com.spring.service.UserService;
import com.spring.validation.UserValidation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
public class ModifyController{
    @Getter
    @Setter(onMethod = @__(@Autowired))
    @ManagedProperty(value = "#{userServiceImpl}")
    private UserService userService;

    @Getter @Setter
    @ManagedProperty(value = "#{listController.user}")
    private User user;

    @Getter @Setter(onMethod = @__(@Autowired))
    @ManagedProperty(value = "#{userValidationImpl}")
    private UserValidation userValidation;

    @Getter @Setter
    private List<User> users;

    @Getter @Setter
    @ManagedProperty(value = "#{listController.user.username}")
    private String originalUsername;

    public void modifyUser(){

        List<User> userList = userService.findAll();
        System.out.println(originalUsername);
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

}
