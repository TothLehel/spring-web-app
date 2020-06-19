package com.spring.controller;

import com.spring.entity.User;
import com.spring.service.UserService;
import com.spring.validation.UserValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.transaction.Transactional;
import java.util.List;

@ManagedBean
@NoArgsConstructor
public class IndexController {

    @Getter @Setter(onMethod = @__(@Autowired))
    @ManagedProperty(value = "#{userServiceImpl}")
    private UserService userService;

    @Getter @Setter(onMethod = @__(@Autowired))
    @ManagedProperty(value = "#{userValidationImpl}")
    private UserValidation userValidation;

    @Getter @Setter
    private User user = new User();

    @Getter @Setter
    private List<User> users;

    @Transactional
    public void saveUser(){
        List<User> userList = userService.findAll(); //itt queryvel kéne kibányászni azokat az embereket akiknek van telefonszámuk
        user=userValidation.saveError(user,userService,userList,true);
        if(user != null) {
            userService.save(user);
            //System.out.println("** DATA INSERTED **");
            user = new User();
            users=userService.findAll();
        }

    }
    @PreDestroy
    public void destroy(){
        user = null;
        users = null;
        userService = null;
        userValidation = null;
    }

}
