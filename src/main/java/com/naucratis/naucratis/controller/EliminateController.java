package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.service.LibraryService;
import com.naucratis.naucratis.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        userService.eliminateLibrary(nameLibrary, principal.getName());

        return "redirect:/administrator/libraries";
    }

    @GetMapping("/{name_library}/book/{id_book}")
    public String eliminateBook(@PathVariable(name = "id_book") String idBook,
                                @PathVariable(name = "name_library") String nameLibrary)
    {
        libraryService.eliminateBook(nameLibrary, idBook);
        return "redirect:/administrator/libraries/" + nameLibrary + "/books";
    }
}