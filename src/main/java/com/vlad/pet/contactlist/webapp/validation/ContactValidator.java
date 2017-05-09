package com.vlad.pet.contactlist.webapp.validation;

import com.vlad.pet.contactlist.model.Contact;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.springframework.validation.ValidationUtils;

@Component
public class ContactValidator implements Validator{
    private final static Logger logger = Logger.getLogger("debug");

    @Override
    public boolean supports(Class<?> aClass) {
        return Contact.class.equals(aClass.getClass());
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "contact.firstName.required");
    }
}
