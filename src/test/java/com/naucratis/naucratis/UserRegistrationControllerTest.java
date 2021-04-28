package com.naucratis.naucratis;

import com.naucratis.naucratis.service.UserService;
import com.naucratis.naucratis.web.UserRegistrationController;
import com.naucratis.naucratis.web.dto.UserRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@WebMvcTest(controllers = UserRegistrationController.class)
public class UserRegistrationControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private BindingResult result;

    @MockBean
    private Model model;

    @Test
    public void validateRegisterOne() {
        UserRegistrationController userRegistrationController = new UserRegistrationController(userService);

        System.out.println("Prueba controlador de registro # 1: Perfección");
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName("Richard Herrera");
        userDto.setCity("Bogotá");
        userDto.setDirection("Carrera 45 N° 26-85");
        userDto.setCel("0123456789");
        userDto.setEmail("roherrerap@unal.edu.co");
        userDto.setPassword("AB@12abc");
        String test = userRegistrationController.registerUserAccount(userDto, result, model);
        assert test.equals("redirect:registro?success");
    }

    @Test
    public void validateRegisterTwo() {
        UserRegistrationController userRegistrationController = new UserRegistrationController(userService);

        System.out.println("Prueba controlador de registro # 2: Contraseña mala");
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName("Richard Herrera");
        userDto.setCity("Bogotá");
        userDto.setDirection("Carrera 45 N° 26-85");
        userDto.setCel("0123456789");
        userDto.setEmail("roherrerap@unal.edu.co");
        userDto.setPassword("AB@12ab");
        String test = userRegistrationController.registerUserAccount(userDto, result, model);
        assert !test.equals("redirect:registro?success");
    }

    @Test
    public void validateRegisterThree() {
        UserRegistrationController userRegistrationController = new UserRegistrationController(userService);

        System.out.println("Prueba controlador de registro # 3: cel malo");
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName("Richard Herrera");
        userDto.setCity("Bogotá");
        userDto.setDirection("Carrera 45 N° 26-85");
        userDto.setCel("0123456789a");
        userDto.setEmail("roherrerap@unal.edu.co");
        userDto.setPassword("AB@12abc");
        String test = userRegistrationController.registerUserAccount(userDto, result, model);
        assert !test.equals("redirect:registro?success");
    }

    @Test
    public void validateRegisterFour() {
        UserRegistrationController userRegistrationController = new UserRegistrationController(userService);

        System.out.println("Prueba controlador de registro # 3: cel malo");
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName("");
        userDto.setCity("Bogotá");
        userDto.setDirection("Carrera 45 N° 26-85");
        userDto.setCel("0123456789a");
        userDto.setEmail("roherrerap@unal.edu.co");
        userDto.setPassword("AB@12abc");
        String test = userRegistrationController.registerUserAccount(userDto, result, model);
        assert !test.equals("redirect:registro?success");
    }
}
