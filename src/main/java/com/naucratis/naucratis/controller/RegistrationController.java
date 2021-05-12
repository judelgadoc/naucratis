package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.form.RegistrationAdministratorForm;
import com.naucratis.naucratis.model.form.RegistrationBookForm;
import com.naucratis.naucratis.model.form.RegistrationCustomerForm;
import com.naucratis.naucratis.model.form.RegistrationLibraryForm;
import com.naucratis.naucratis.model.library.Author;
import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.model.user.Administrator;
import com.naucratis.naucratis.service.AuthorService;
import com.naucratis.naucratis.service.BookService;
import com.naucratis.naucratis.service.LibraryService;
import com.naucratis.naucratis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/register")
@SessionAttributes("role")
public class RegistrationController
{
    private UserService    userService;
    private LibraryService libraryService;
    private BookService    bookService;
    private AuthorService  authorService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, LibraryService libraryService, BookService bookService, AuthorService authorService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.libraryService = libraryService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm()
    {
        return "user_no_register/start_session/registration";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/customer")
    public String registerFormCustomer()
    {
        return "user_no_register/start_session/form_customer";
    }

    @PostMapping("/customer")
    public String processRegistrationCustomer(RegistrationCustomerForm registrationCustomerForm)
    {
        userService.save(registrationCustomerForm.toCustomer(passwordEncoder));
        return "redirect:/login";
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/administrator")
    public String registerFormAdministrator()
    {
        return "user_no_register/start_session/form_administrator";
    }

    @PostMapping("/administrator")
    public String processRegistrationAdministrator(RegistrationAdministratorForm registrationAdministratorForm)
    {
        userService.save(registrationAdministratorForm.toAdministrator(passwordEncoder));
        return "redirect:/login";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/library")
    public String registerFormLibrary()
    {
        return "administrator/register/form_library";
    }

    @PostMapping("/library")
    public String processRegistrationLibrary(RegistrationLibraryForm registrationLibraryForm, Principal principal)
    {
        Administrator admin
                = (Administrator) userService.findByEmail(principal.getName());

        Library library = registrationLibraryForm.toLibrary();

        admin.getLibraries().add(library);
        libraryService.save(library);
        return "redirect:/administrator";
    }

    @GetMapping("/book/{nameLibrary}")
    public String registerFormBook(@PathVariable String nameLibrary, Model model)
    {
        model.addAttribute("nameLibrary", nameLibrary);
        return "administrator/register/form_book";
    }

    @PostMapping("/book")
    public String processRegistrationBook(RegistrationBookForm registrationBookForm)
    {

        Book book = null;
        try {
            book = registrationBookForm.toBook();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Author author: book.getAuthors())
            if(!authorService.existsByName(author.getName()))
                authorService.save(author);

        libraryService.findByName(registrationBookForm.getNameLibrary()).getInventory().add(book);
        bookService.save(book);
        return"redirect:/administrator/libraries/"+registrationBookForm.getNameLibrary();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
