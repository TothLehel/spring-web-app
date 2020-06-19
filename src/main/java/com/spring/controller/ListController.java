package com.spring.controller;

import com.spring.entity.User;
import com.spring.service.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@NoArgsConstructor
@SessionScoped
public class ListController implements Serializable {

    @Getter @Setter(onMethod = @__(@Autowired))
    @ManagedProperty(value = "#{userServiceImpl}")
    private UserService userService;

    @Getter @Setter
    private User user = new User();

    @Getter @Setter
    private List<User> users;

    @PreDestroy
    public void destroy(){
        user = null;
        users = null;
        userService = null;
    }

    @PostConstruct
    public void listUsers(){
        user = new User();
        users = userService.findAll();
        for (User theUser: users) {
            System.out.println(theUser.toString());
        }
    }
    public void modifyUser(User tempUser){
        user = tempUser;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("modify.xhtml?i=2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(User userToDelete){
        userService.deleteById(userToDelete.getUsername());
        users = userService.findAll();
    }

}
