package com.vlad.pet.contactlist.webapp.util;

import com.vlad.pet.contactlist.model.service.UserService;
import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.webapp.config.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//provides an instance of User class
//which represents current session user
@Component
public class UserInstanceProvider {
    @Autowired
    UserService userService;

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((CurrentUser) auth.getPrincipal()).getUser();
        return userService.findByNickName(currentUser.getNickName());
    }
}
