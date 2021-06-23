package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.user.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;


public class RegistrationCustomerForm extends RegistrationUserForm
{
    public Customer toCustomer(PasswordEncoder encoder)
    {
        Customer customer =  new Customer
                (getName(), getAddress(), getCity(), getPhone(), getEmail(), encoder.encode(getPassword()));

        try {
            customer.setImage(getImage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customer;
    }
}
