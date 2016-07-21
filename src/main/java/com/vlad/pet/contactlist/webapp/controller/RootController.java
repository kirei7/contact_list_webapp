package com.vlad.pet.contactlist.webapp.controller;

import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.Contact;
import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.model.user.UserForm;
import com.vlad.pet.contactlist.model.service.UserService;
import com.vlad.pet.contactlist.webapp.util.UserInstanceProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController {

    private final static Logger logger = Logger.getLogger("debug");
    @Autowired
    private ApplicationManager manager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInstanceProvider userInstanceProvider;

    @RequestMapping("register")
    public String registerUserForm(Model model) {
        model.addAttribute(
                "userForm",
                new UserForm()
                );
        return "register";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getContacts(Model model) {
        User user = getUser();
        model.addAttribute(
                "contactList",
                manager.getAllUserContacts(user)
        );
        model.addAttribute(
                "contact",
                new Contact()
        );
        return "contactlist";
    }

    private User getUser() {
        return userInstanceProvider.getUser();
    }
}
