package com.naucratis.naucratis.service;


import com.naucratis.naucratis.model.Client;
import com.naucratis.naucratis.model.User;
import com.naucratis.naucratis.repository.UserRepository;
import com.naucratis.naucratis.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) throw new UsernameNotFoundException("Usuario y/o contraseña incorrecta.");
        return user;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(
                userRegistrationDto.getName(),
                userRegistrationDto.getDirection(),
                userRegistrationDto.getCity(),
                userRegistrationDto.getCel(),
                userRegistrationDto.getEmail(),
                passwordEncoder.encode(userRegistrationDto.getPassword()));
        return userRepository.save(user);
    }
}
