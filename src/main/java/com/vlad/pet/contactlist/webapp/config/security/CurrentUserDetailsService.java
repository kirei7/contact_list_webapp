package com.vlad.pet.contactlist.webapp.config.security;

import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = userService.findByNickName(nickName);
        if (user == null) throw new UsernameNotFoundException(String.format("User with nickname=%s was not found", nickName));
        return new CurrentUser(user);
    }
}
