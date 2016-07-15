package com.vlad.pet.contactlist.webapp.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.Contact;
import com.vlad.pet.contactlist.model.User;
import com.vlad.pet.contactlist.model.service.ContactService;
import com.vlad.pet.contactlist.model.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping(value = "/contacts")
public class ContactController {
    //directory with contact's templates
    private String path = "contacts/";

    private final static Logger logger = Logger.getLogger("debug");
    @Autowired
    private ApplicationManager manager;
    @Autowired
    private UserService userService;
    @Autowired ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public String getContacts(Model model) {
        prepareModel(model);
        return path + "contactlist";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addContact(@ModelAttribute Contact contact, Model model) {
        Contact added = manager.addContactToUserList(getUser(), contact);
        model.addAttribute(
                "addedContact",
                contact
        );
        prepareModel(model);
        return path + "contactlist";
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteContact(@ModelAttribute Contact contact, Model model) {
        Contact deleted = manager.removeContactFromUserList(
                getUser(),
                contactService.findById(contact.getId())
        );
        model.addAttribute(
                "deletedContact",
                deleted
        );
        prepareModel(model);
        return path + "contactlist";
    }

    private void prepareModel(Model model) {
        User user = getUser();
        model.addAttribute(
                "contactList",
                manager.getAllUserContacts(user)
        );
        model.addAttribute(
                "contact",
                new Contact()
        );
    }
    private User getUser() {
        return userService.findByNickName("vlad12");
    }
}
