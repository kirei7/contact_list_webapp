package com.vlad.pet.contactlist.webapp.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.Contact;
import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.model.service.ContactService;
import com.vlad.pet.contactlist.model.service.UserService;
import com.vlad.pet.contactlist.webapp.util.UserInstanceProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
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
    @Autowired
    private UserInstanceProvider userInstanceProvider;

    @RequestMapping(method = RequestMethod.GET)
    public Set<Contact> getAllContacts() {
        return manager.getAllUserContacts(
                getUser()
        );
    }
    @RequestMapping(method = RequestMethod.POST)
    public Contact addContact(@ModelAttribute Contact contact, Model model) {
        return manager.addContactToUserList(getUser(), contact);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public Contact deleteContact(@ModelAttribute Contact contact, Model model) {
        logger.debug(getUser());
        return manager.removeContactFromUserList(
                getUser(),
                contactService.findById(contact.getId())
        );
    }

    private User getUser() {
        return userInstanceProvider.getUser();
    }
}
