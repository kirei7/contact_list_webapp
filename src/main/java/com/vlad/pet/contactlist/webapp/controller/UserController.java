package com.vlad.pet.contactlist.webapp.controller;

import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.User;
import com.vlad.pet.contactlist.model.UserForm;
import com.vlad.pet.contactlist.model.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    //directory with user's templates
    private String path = "users/";

    private final static Logger logger = Logger.getLogger("debug");
    @Autowired
    private ApplicationManager manager;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        model.addAttribute(
                "user",
                userService.findByID(id));
        return "users";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUserAct(@ModelAttribute UserForm userForm, Model model) {
        User registered = manager.registerUser(userForm);
        model.addAttribute("user", registered);
        return path + "register-success";
    }

}
