package com.vlad.pet.contactlist.webapp.util;

import com.vlad.pet.contactlist.model.user.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickName", "register.nickName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "register.password.required");

        UserForm userForm = (UserForm) obj;
        int nickNameL = userForm.getNickName().length(),
                passwordL = userForm.getPassword().length();
        if (nickNameL < NICKNAME_MIN_LENGTH | nickNameL > NICKNAME_MAX_LENGTH) {
            errors.reject("register.nickName.length", "Nickname must contain at least 5 characters, but not more than 16");
        }
        if (nickNameL < PASSWORD_MIN_LENGTH | nickNameL > PASSWORD_MAX_LENGTH) {
            errors.reject("register.password.length", "Password must contain at least 5 characters, but not more than 16");
        }
    }
}
