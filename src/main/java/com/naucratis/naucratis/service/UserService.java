package com.naucratis.naucratis.service;


import com.naucratis.naucratis.model.User;
import com.naucratis.naucratis.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
