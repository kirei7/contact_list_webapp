package com.vlad.pet.contactlist.webapp;

import com.vlad.pet.contactlist.model.user.UserForm;
import com.vlad.pet.contactlist.webapp.util.UserFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserFormValidatorTest {
    private UserFormValidator validator;
    private Errors errors;

    @Before
    public void setUp() {
        validator = new UserFormValidator();
        errors = Mockito.mock(Errors.class);
    }

    @Test
    public void emptyFieldsRejected() {
        validator.validate(new UserForm().withNickName("").withPassword("dsdsds3"), errors);
        verify(errors, atLeast(1)).rejectValue(anyString(), anyString(), anyObject(), anyObject());
    }
    @Test
    public void callToRejectIfFieldsIsInvalid() {
        for (UserForm form : getInvalidUserForms()) {
            validator.validate(form, errors);
            verify(errors, atLeast(1)).reject(anyString(), anyString());
        }
    }

    private List<UserForm> getInvalidUserForms() {
        List<UserForm> userForms = new ArrayList<>();
        userForms.add(new UserForm()
                //short nickName
                .withNickName("shrt")
                .withPassword("normalPass12")
        );
        userForms.add(new UserForm()
                //short password
                .withNickName("normalName12")
                .withPassword("shrt")
        );
        userForms.add(new UserForm()
                //too long nickName
                .withNickName("notNormalLongNickName12")
                .withPassword("normalPass12")
        );
        userForms.add(new UserForm()
                //too long password
                .withNickName("normalName12")
                .withPassword("notNormalLongNickPass12")
        );
        return userForms;
    }
}
