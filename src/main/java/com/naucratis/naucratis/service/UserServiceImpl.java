package com.naucratis.naucratis.service;


import com.naucratis.naucratis.model.Client;
import com.naucratis.naucratis.repository.UserRepository;
import com.naucratis.naucratis.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public Client save(UserRegistrationDto userRegistrationDto) {
        Client client = new Client(
                userRegistrationDto.getName(),
                userRegistrationDto.getDirection(),
                userRegistrationDto.getCity(),
                userRegistrationDto.getCel(),
                userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword());
        return userRepository.save(client);
    }
}
