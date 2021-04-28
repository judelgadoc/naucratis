package com.naucratis.naucratis.web;


import com.naucratis.naucratis.service.UserService;
import com.naucratis.naucratis.web.dto.UserRegistrationDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/registro")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registro";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("client") UserRegistrationDto userRegistrationDto, BindingResult result, Model model) {
        if (!userRegistrationDto.getCel().matches("^[0-9]{8}$|^[0-9]{10}$")) {
            model.addAttribute("celError", userRegistrationDto);
            return "/registro";
        }
        if (!userRegistrationDto.getPassword().matches("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$")) {
            model.addAttribute("passError", userRegistrationDto);
            return "/registro";
        }
        try {
            userService.save(userRegistrationDto);
            return "redirect:registro?success";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorEmail", userRegistrationDto);
            return "/registro";
        }

    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
}
