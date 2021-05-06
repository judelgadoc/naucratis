package com.naucratis.naucratis.web;


import com.naucratis.naucratis.service.UserService;
import com.naucratis.naucratis.web.dto.UserRegistrationDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/register_admin")
public class AdminRegistrationController {

    private UserService userService;

    public AdminRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register_admin";
    }

    @PostMapping
    public String registerClientAccount(@Valid @ModelAttribute("admin") UserRegistrationDto userRegistrationDto, BindingResult result, Model model) {
        if (!userRegistrationDto.getCel().matches("^[0-9]{8}$|^[0-9]{10}$")) {
            model.addAttribute("celError", userRegistrationDto);
            return "/register_admin";
        }
        if (!userRegistrationDto.getPassword().matches("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$")) {
            model.addAttribute("passError", userRegistrationDto);
            return "/register_admin";
        }
        try {
            userService.save(userRegistrationDto, "admin");
            return "redirect:register_admin?success";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorEmail", userRegistrationDto);
            return "/register_admin";
        }

    }

    @ModelAttribute("admin")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
}
