package com.naucratis.naucratis.service;


import com.naucratis.naucratis.model.User;
import com.naucratis.naucratis.web.dto.UserRegistrationDto;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
