package com.naucratis.naucratis.model.form;

import lombok.Data;

@Data
public class RegistrationUserForm
{
    private String name;
    private String address;
    private String city;
    private String phone;
    private String email;
    private String password;

}
