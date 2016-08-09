package com.vlad.pet.contactlist.webapp.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.vlad.pet.contactlist.model.ApplicationManager;
import com.vlad.pet.contactlist.model.Contact;
import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.model.service.ContactService;
import com.vlad.pet.contactlist.model.service.UserService;
import com.vlad.pet.contactlist.webapp.util.UserInstanceProvider;
import com.vlad.pet.contactlist.webapp.validation.ContactValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
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
    @Autowired
    ContactService contactService;
    @Autowired
    private UserInstanceProvider userInstanceProvider;
    @Autowired
    private ContactValidator validator;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public Set<Contact> getAllContacts() {
        return manager.getAllUserContacts(
                getUser()
        );
    }
    @RequestMapping(method = RequestMethod.POST)
    public Contact addContact(@ModelAttribute Contact contact,
                              Model model,
                              HttpServletResponse httpServletResponse,
                              BindingResult result) {
        if (!validator.supports(contact.getClass())) {
            logger.debug("Object is not supported: " + contact);
        }
        validator.validate(contact, result);
        if (result.hasErrors()) {
            try {
                httpServletResponse.sendError(406, result.getFieldErrors().get(0).getCode());
            } catch (IOException ex) {
                logger.debug(ex);
            }
            return null;
        }
        return manager.addContactToUserList(getUser(), contact);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public Contact deleteContact(@ModelAttribute Contact contact, Model model) {
        return manager.removeContactFromUserList(
                getUser(),
                contactService.findById(contact.getId())
        );
    }
    @RequestMapping("/resolve-error")
    public String resolveErrorMsg(@RequestParam("code") String code, Locale loc) {
        logger.debug(code);
        return messageSource.getMessage(code, null, loc);
    }
    private User getUser() {
        return userInstanceProvider.getUser();
    }
}
