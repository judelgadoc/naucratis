package com.naucratis.naucratis;

import com.naucratis.naucratis.model.User;
import com.naucratis.naucratis.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmailTest() {
        User user = new User(
                "nombrePrueba",
                "direccionPrueba",
                "ciudadPrueba",
                "1234567890",
                "prueba@prueba.com",
                "PasswordPrueba123!#"
        );
        userRepository.save(user);

        User userfound = userRepository.findByEmail("prueba@prueba.com");

        assertEquals(user, userfound);
    }
}
