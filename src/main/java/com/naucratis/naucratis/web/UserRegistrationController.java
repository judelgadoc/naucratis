package com.naucratis.naucratis.web;


import com.naucratis.naucratis.service.UserService;
import com.naucratis.naucratis.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("client") UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        return "redirect:registration?success";
    }

    @ModelAttribute("client")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
}
