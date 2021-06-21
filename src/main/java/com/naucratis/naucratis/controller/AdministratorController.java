package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.model.user.Administrator;
import com.naucratis.naucratis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/administrator")
@SessionAttributes("role")
public class AdministratorController
{
    private UserService userService;

    public AdministratorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profile(Principal principal,
                          Model model)
    {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());

        model.addAttribute("administrator", administrator);
        return "administrator/profile";
    }

    @GetMapping("/libraries")
    public String libraries(Principal principal,
                            Model model)
    {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());
        model.addAttribute("libraries", administrator.getLibraries());
        return "administrator/library/list_libraries";
    }

    @GetMapping("/libraries/{name_library}")
    public String library(@PathVariable(name = "name_library") String nameLibrary,
                          Principal principal,
                          Model model)
    {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());

        for(Library library: administrator.getLibraries())
            if(library.getName().equals(nameLibrary))
                model.addAttribute("library", library);

        return "administrator/library/library";
    }

    @GetMapping("/libraries/{name_library}/books")
    public String books(@PathVariable(name = "name_library") String nameLibrary,
                        Principal principal,
                        Model model) throws Exception {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());

        Library library = null;

        for(Library lib: administrator.getLibraries())
            if(nameLibrary.equals(lib.getName()))
                library = lib;

        if(library == null)
            throw new Exception("La libraria no esta asociada con el administrador de la sesión");

        model.addAttribute("library", library.getName());

        model.addAttribute("books", library.getInventory());

        return "administrator/library/list_books";
    }

    @GetMapping("/libraries/{name_library}/{id_book}")
    public String book(@PathVariable(name = "name_library") String nameLibrary,
                       @PathVariable(name = "id_book") String idBook,
                       Principal principal,
                       Model model) throws Exception {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());

        Library library = null;

        for(Library lib: administrator.getLibraries())
            if(nameLibrary.equals(lib.getName()))
                library = lib;

        if(library == null)
            throw new Exception("La libraria no esta asociada con el administrador de la sesión");

        Book book = null;

        for(Book bk: library.getInventory())
            if(bk.getId() == Long.parseLong(idBook))
                book = bk;

        if(book == null)
            throw new Exception("El libro no esta en la libreria");

        model.addAttribute("book", book);
        model.addAttribute("name_library", nameLibrary);

        return "administrator/library/book";
    }
}
