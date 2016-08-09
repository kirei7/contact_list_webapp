package com.vlad.pet.contactlist.webapp.controller;

import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.model.user.UserForm;
import com.vlad.pet.contactlist.model.service.UserService;
import com.vlad.pet.contactlist.webapp.validation.UserFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
    public String registerUserAct(@Valid @ModelAttribute UserForm userForm, BindingResult result, Model model) {
        if (!validator.supports(userForm.getClass())) return path + "register-failed";
        validator.validate(userForm, result);
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) logger.debug(error.getCode());
            return path + "register-failed";
        }
        User registered = manager.registerUser(userForm);
        model.addAttribute("user", registered);
        return path + "register-success";
    }

}
