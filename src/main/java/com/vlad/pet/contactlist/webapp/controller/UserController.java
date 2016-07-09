package com.vlad.pet.contactlist.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping(value = "/user/{id}")
    public String helloTest(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "user";
    }
}
