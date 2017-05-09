package com.vlad.pet.contactlist.webapp.validation;

import com.vlad.pet.contactlist.model.user.UserForm;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {

    private final static Logger logger = Logger.getLogger("debug");

    private final int
    NICKNAME_MIN_LENGTH = 5,
    NICKNAME_MAX_LENGTH = 16,
    PASSWORD_MIN_LENGTH = 5,
    PASSWORD_MAX_LENGTH = 16;

    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return UserForm.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserForm userForm = (UserForm) obj;
        String name = userForm.getNickName(),
                pass = userForm.getPassword();
        if (name == null || name.isEmpty()) {
            errors.rejectValue("nickName","register.nickName.required");
            return;
        }
        if (pass == null || pass.isEmpty()) {
            errors.rejectValue("password","register.password.required");
            return;
        }
        int nickNameL = name.length(),
                passwordL = pass.length();
        if (nickNameL < NICKNAME_MIN_LENGTH | nickNameL > NICKNAME_MAX_LENGTH) {
            errors.rejectValue("nickName","register.nickName.length", "Nickname must contain at least 5 characters, but not more than 16");
        }
        if (passwordL < PASSWORD_MIN_LENGTH | passwordL > PASSWORD_MAX_LENGTH) {
            errors.rejectValue("password","register.password.length", "Password must contain at least 5 characters, but not more than 16");
        }
    }
}
