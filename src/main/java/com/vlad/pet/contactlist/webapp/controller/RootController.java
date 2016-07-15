package com.vlad.pet.contactlist.webapp.controller;

import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.Contact;
import com.vlad.pet.contactlist.model.User;
import com.vlad.pet.contactlist.model.UserForm;
import com.vlad.pet.contactlist.model.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    private final static Logger logger = Logger.getLogger("debug");
    @Autowired
    private ApplicationManager manager;
    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public String registerUserForm(Model model) {
        model.addAttribute(
                "userForm",
                new UserForm()
                );
        return "register";
    }


    private User getUser() {
        return userService.findByNickName("vlad12");
    }
}