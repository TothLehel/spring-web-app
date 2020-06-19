package com.spring.validation;


import com.spring.entity.User;
import com.spring.service.UserService;

import java.util.List;

public interface UserValidation {
    User saveError(User user, UserService userService, List<User> userList, boolean newUsername);
    //User modiError(User user, UserService userService, List<User> userList);
    public String parseTelephoneNumber(String originalNumber);
}
