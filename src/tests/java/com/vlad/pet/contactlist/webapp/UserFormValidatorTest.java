package com.vlad.pet.contactlist.webapp;

import com.vlad.pet.contactlist.model.user.UserForm;
import com.vlad.pet.contactlist.webapp.validation.UserFormValidator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


public class UserFormValidatorTest {
    private static Logger logger = Logger.getLogger("debug");

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
        validator.validate(new UserForm().withNickName("dsdsds3").withPassword(""), errors);
        validator.validate(new UserForm().withNickName("").withPassword(""), errors);
        validator.validate(new UserForm().withNickName(null).withPassword("dsdsds3"), errors);
        validator.validate(new UserForm().withNickName("dsdsds3").withPassword(null), errors);
        //every time invalid value must be rejected once
        verify(errors, times(5)).rejectValue(anyString(), anyString());
    }
    @Test
    public void callToRejectIfFieldsIsInvalid() {
        List<UserForm> forms = getInvalidUserForms();
        for (UserForm form : forms) {
            validator.validate(form, errors);
        }
        verify(errors, times(forms.size())).rejectValue(anyString(), anyString(), anyString());
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
