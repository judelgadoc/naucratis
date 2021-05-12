package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.user.Administrator;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationAdministratorForm extends RegistrationUserForm
{

    public Administrator toAdministrator(PasswordEncoder encoder)
    {
        Administrator administrator = new Administrator
                (getName(), getAddress(), getCity(), getPhone(), getEmail(), encoder.encode(getPassword()));

        return administrator;
    }
}

