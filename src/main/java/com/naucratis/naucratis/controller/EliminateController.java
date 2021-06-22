package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.service.LibraryService;
import com.naucratis.naucratis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/eliminate")
public class EliminateController {

    private LibraryService libraryService;
    private UserService    userService;

    public EliminateController(LibraryService libraryService,
                               UserService userService)
    {
        this.userService = userService;
        this.libraryService = libraryService;
    }

    @GetMapping("/library/{name_library}")
    public String eliminateLibrary(@PathVariable(name = "name_library") String nameLibrary,
                                   Principal principal)
    {
        libraryService.eliminateLibrary(nameLibrary, principal.getName());

        return "redirect:/administrator/libraries";
    }

    @GetMapping("/site")
    public String eliminateSite(@RequestParam("address") String addressSite,
                                @RequestParam("library") String nameLibrary)
    {

        libraryService.eliminateSite(nameLibrary, addressSite);
        return "redirect:/administrator/libraries/" + nameLibrary;
    }

    @GetMapping("/{name_library}/book/{id_book}/{isbn}")
    public String eliminateBook(@PathVariable(name = "id_book") String idBook,
                                @PathVariable String isbn,
                                @PathVariable(name = "name_library") String nameLibrary)
    {
        boolean lastCopy =
                libraryService.eliminateBook(nameLibrary, Long.parseLong(idBook), Long.parseLong(isbn));

        return "redirect:/administrator/libraries/" + nameLibrary + (lastCopy == true ? "/books": "/" + isbn);
    }

    @GetMapping("/shoppingCart/order/{idOrder}")
    public String eliminateOrderShoppingCart(@PathVariable String idOrder)
    {
        userService.eliminateOrder(Long.parseLong(idOrder));
        return "redirect:/customer/shoppingCart";
    }

}