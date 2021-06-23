package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.user.Administrator;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

public class RegistrationAdministratorForm extends RegistrationUserForm
{

    public Administrator toAdministrator(PasswordEncoder encoder)
    {
        Administrator administrator = new Administrator
                (getName(), getAddress(), getCity(), getPhone(), getEmail(), encoder.encode(getPassword()));

        try {
            administrator.setImage(getImage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return administrator;
    }
}

