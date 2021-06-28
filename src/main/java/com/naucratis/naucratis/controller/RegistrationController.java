package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.form.*;
import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.model.user.Administrator;
import com.naucratis.naucratis.service.BookService;
import com.naucratis.naucratis.service.LibraryService;
import com.naucratis.naucratis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/register")
@SessionAttributes("role")
public class RegistrationController
{
    private UserService    userService;
    private LibraryService libraryService;
    private BookService    bookService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, LibraryService libraryService,
                                  PasswordEncoder passwordEncoder, BookService bookService) {
        this.userService     = userService;
        this.libraryService  = libraryService;
        this.passwordEncoder = passwordEncoder;
        this.bookService     = bookService;
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
        libraryService.addLibrary(registrationLibraryForm.toLibrary(),
                                  (Administrator) userService.findByEmail(principal.getName()));

        return "redirect:/administrator";
    }

    @GetMapping("/book/{libraryId}")
    public String registerFormBook(@PathVariable long libraryId, Model model)
    {
        model.addAttribute("libraryId", libraryId);
        model.addAttribute("sites", libraryService.getLibraryById(libraryId).get().getSites());
        return "administrator/register/form_book";
    }

    @PostMapping("/book")
    public String processRegistrationBook(RegistrationBookForm registrationBookForm)
    {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Processing registration book");
        System.out.println("RegistrationBookForm: ");
        System.out.println(registrationBookForm.toString());
        libraryService.addBooks(registrationBookForm);
        return"redirect:/administrator/libraries/"+registrationBookForm.getLibraryId();
    }

    @GetMapping("/copyBook/{nameLibrary}/{isbn}")
    public String registerCopyBook(@PathVariable String nameLibrary,
                                   @PathVariable String isbn,
                                   Model model)
    {
        model.addAttribute("nameLibrary", nameLibrary);
        model.addAttribute("isbn", isbn);
        model.addAttribute("sites", libraryService.findByName(nameLibrary).getSites());

        return "administrator/register/form_copyBook";
    }

    @PostMapping("/copyBookProcess")
    public String processRegistrarionCopyBook(CopyBookForm copyBookForm)
    {
        libraryService.addBook(copyBookForm);
        return "redirect:/administrator/libraries/" + copyBookForm.getNameLibrary() + "/" + copyBookForm.getIsbn();
    }

    @PostMapping("/search_book/{nameLibrary}")
    public String searchBook(@PathVariable String nameLibrary, String isbn, Model model)
    {
        Book book = bookService.exitsByIsbn(Long.parseLong(isbn))? bookService.findById(isbn):null;

        model.addAttribute("nameLibrary", nameLibrary);
        model.addAttribute("sites", libraryService.findByName(nameLibrary).getSites());
        model.addAttribute("book", book);

        return "administrator/register/form_book";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/{name_library}/site")
    public String site(@PathVariable(name = "name_library") String nameLibrary,
                       Model model)
    {
        model.addAttribute("name_library", nameLibrary);
        return "administrator/register/form_site";
    }

    @PostMapping("/{name_library}/site")
    public String siteProcess(@PathVariable(name = "name_library") String nameLibrary,
                              RegisterSiteForm registerSiteForm)
    {
        libraryService.addSite(nameLibrary, registerSiteForm.toSite());
        return "redirect:/administrator/libraries/" + nameLibrary;
    }
}
