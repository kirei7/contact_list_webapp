package com.vlad.pet.contactlist.webapp.controller;

import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.User;
import com.vlad.pet.contactlist.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UtilController {


    @Autowired
    private ApplicationManager manager;
    @Autowired
    private UserService userService;

    @RequestMapping("util/preparedContactList")
    public String getPreparedContactList(Model model) {
        model.addAttribute(
                "contactList",
                manager.getAllUserContacts(getUser())
        );
        return "contacts/preparedContactList";
    }

    private User getUser() {
        return userService.findByNickName("vlad12");
    }
}
