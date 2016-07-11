package com.vlad.pet.contactlist.webapp.controller;

import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.Contact;
import com.vlad.pet.contactlist.model.User;
import com.vlad.pet.contactlist.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private ApplicationManager manager;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/user/{id}")
    public String helloTest(@PathVariable Long id, Model model) {
        User user = userService.findByID(id);
        model.addAttribute("id", user.getNickName());
        return "user";
    }
}
