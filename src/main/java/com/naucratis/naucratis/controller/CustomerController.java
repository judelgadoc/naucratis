package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.user.Customer;
import com.naucratis.naucratis.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/customer")
@SessionAttributes("role")
public class CustomerController
{
    UserRepository userRepository;

    public CustomerController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String profile(Principal principal,
                          Model model)
    {
        Customer customer = (Customer) userRepository.findByEmail(principal.getName());
        model.addAttribute("customer", customer);
        return "customer/profile";
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(Principal principal,
                               Model model)
    {
        Customer customer = (Customer) userRepository.findByEmail(principal.getName());
        model.addAttribute("email", customer.getEmail());
        model.addAttribute("orders",  customer.getShoppingCart());

        return "customer/shopping_cart";
    }

}

