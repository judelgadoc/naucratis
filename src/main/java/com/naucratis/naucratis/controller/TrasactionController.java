package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.service.LibraryService;
import com.naucratis.naucratis.service.UserService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/transaction")
public class TrasactionController
{
    @Autowired
    private UserService    userService;
    @Autowired
    private LibraryService libraryService;



    @GetMapping("/start/{nameLibrary}/{idBook}")
    public String startPurchase(Principal principal,
                                @PathVariable String nameLibrary,
                                @PathVariable String idBook)
    {
        userService.startPurchase(principal.getName(), Long.parseLong(idBook), nameLibrary);
        return "redirect:/customer/shoppingCart";
    }

    @GetMapping("/processPurchase")
    public String processPurchase(Principal principal)
    {
        userService.processPurchase(principal.getName());
        return "redirect:/customer";
    }

    @GetMapping("/finishing/{libraryId}/{idSale}")
    public String finishing(@PathVariable String idSale,
                           @PathVariable long libraryId)
    {
        libraryService.finished(idSale);
        return "redirect:/administrator/libraries/" + libraryId;
    }

    @GetMapping("/cancelling/{libraryId}/{idSale}")
    public String cancelling(@PathVariable String idSale,
                             @PathVariable long libraryId)
    {
        try {
            libraryService.cancelled(idSale, libraryId);
        } catch (Exception e) {
            return "redirect:/administrator/libraries/" + libraryId + "?failure";
        }
        return "redirect:/administrator/libraries/" + libraryId;
    }
}
