package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.user.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;


public class RegistrationCustomerForm extends RegistrationUserForm
{
    public Customer toCustomer(PasswordEncoder encoder)
    {
        Customer customer =  new Customer
                (getName(), getAddress(), getCity(), getPhone(), getEmail(), encoder.encode(getPassword()));

        return customer;
    }
}
