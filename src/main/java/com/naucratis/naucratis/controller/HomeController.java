package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.user.User;
import com.naucratis.naucratis.service.LibraryService;
import com.naucratis.naucratis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Locale;

@Controller
@RequestMapping("/")
@SessionAttributes("role")
public class HomeController
{
    private UserService    userService;
    private LibraryService libraryService;

    public HomeController(UserService userService, LibraryService libraryService) {
        this.userService = userService;
        this.libraryService = libraryService;
    }

    @GetMapping
    public String home(Model model, Principal principal)
    {
        System.out.println("Entering home xD");
        if(principal != null)
        {
            User user = userService.findByEmail(principal.getName());
            String role =
                    user
                            .getClass()
                            .getName()
                            .replace("com.naucratis.naucratis.model.user.","")
                            .toLowerCase(Locale.ROOT);

            model.addAttribute("role", role);
        }

        return "user_no_register/home";
    }
}
