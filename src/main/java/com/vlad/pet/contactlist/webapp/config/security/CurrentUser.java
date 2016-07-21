package com.vlad.pet.contactlist.webapp.config.security;

import com.vlad.pet.contactlist.model.user.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;
    public CurrentUser(User user) {
        super(user.getNickName(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}
