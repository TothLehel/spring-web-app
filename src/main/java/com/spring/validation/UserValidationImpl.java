package com.spring.validation;

import com.spring.entity.User;
import com.spring.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

@NoArgsConstructor(onConstructor = @__(@Autowired))
@ManagedBean
public class UserValidationImpl implements UserValidation {
    //ezt a servicebe kéne átrakni
    @Override
    public User saveError(User user, UserService userService, List<User> userList, boolean newUsername) {
        try{
            if(newUsername) {
                userAlreadyExists(user, userService);
            }
            user = phoneNumberIsTaken(user,userList);
        }catch (Exception exception){
            catchExeptions(exception,user);
            return null;
        }
        return user;
    }
    @Override
    public String parseTelephoneNumber(String originalNumber){
        return originalNumber.replaceAll("\\D","");
    }

    private User phoneNumberIsTaken(User user, List<User> userList) throws Exception{
        if(user.getMobileNumber().trim().isEmpty()){ //alapból nem null-t ír be ha üres a mezőt
            user.setMobileNumber(null);
        }else{
            user.setMobileNumber(parseTelephoneNumber(user.getMobileNumber()));
            for (User value : userList) {
                if (value.getMobileNumber() != null && value.getMobileNumber().equals(user.getMobileNumber())) {
                    throw new Exception("mobileNumber");
                }
            }
        }
        if (user.getHomeNumber().trim().isEmpty()){
            user.setHomeNumber(null);
        }else {
            user.setHomeNumber(parseTelephoneNumber(user.getHomeNumber()));
            for (User value : userList) {
                if (value.getHomeNumber() != null && value.getHomeNumber().equals(user.getHomeNumber())) {
                    throw new Exception("homeNumber");
                }
            }
        }
        return user;
    }

    private void userAlreadyExists(User user, UserService userService) throws Exception{
        if(userService.getUserById(user.getUsername()) != null){
            throw new Exception("username");
        }
    }
    private void catchExeptions(Exception exception, User user) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        System.out.println("** ERROR **");
        FacesMessage facesMessage;
        switch (exception.getMessage()) {
            case "username":
                System.out.println("username taken");
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                        "Username already taken!");
                facesContext.addMessage("form:username", facesMessage);
                break;
            case "mobileNumber":
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                        "the: " + user.getMobileNumber() + " number is already taken!");
                facesContext.addMessage("form:mobileNumber", facesMessage);
                break;
            case "homeNumber":
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                        "the: " + user.getHomeNumber() + " number is already taken!");
                facesContext.addMessage("form:homeNumber", facesMessage);
                break;
            default:
                exception.printStackTrace();
        }
    }



}
