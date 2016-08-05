package com.vlad.pet.contactlist.webapp.controller;

import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.model.user.UserForm;
import com.vlad.pet.contactlist.model.service.UserService;
import com.vlad.pet.contactlist.webapp.util.UserFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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
    @Autowired
    private UserFormValidator validator;

    @RequestMapping(value = "/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        model.addAttribute(
                "user",
                userService.findByID(id));
        return "users";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUserAct(@ModelAttribute @Validated UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.debug(result.getAllErrors());
            return path + "register-failed";
        }
        User registered = manager.registerUser(userForm);
        model.addAttribute("user", registered);
        return path + "register-success";
    }

}
