package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.CopyBook;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.model.user.User;
import com.naucratis.naucratis.service.BookService;
import com.naucratis.naucratis.service.LibraryService;
import com.naucratis.naucratis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
@SessionAttributes("role")
public class HomeController
{
    private UserService    userService;
    private LibraryService libraryService;
    private BookService    bookService;

    public HomeController(BookService bookService,
                          UserService userService,
                          LibraryService libraryService) {
        this.userService = userService;
        this.libraryService = libraryService;
        this.bookService = bookService;
    }

    @GetMapping
    public String home(Model model, Principal principal)
    {
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

    @GetMapping("libraries")
    public String list(Model model)
    {
        model.addAttribute("libraries", libraryService.findAll());
        return "user_no_register/library/list_libraries";
    }

    @GetMapping("libraries/{nameLibrary}")
    public String librarySpec(@PathVariable String nameLibrary, Model model)
    {
        Library library = libraryService.findByName(nameLibrary);
        List<Book> books = new ArrayList<>();

        for(Book bk: library.getBooks())
            for(CopyBook copyBook: library.getInventory())
                if(copyBook.getIsbn() == bk.getIsbn() && copyBook.getStatus().equals(CopyBook.Status.AVAILABLE)) {
                    books.add(bk);
                    break;
                }
        model.addAttribute("books", books);
        model.addAttribute("nameLibrary", library.getName());
        model.addAttribute("image", library.getImageBase64());
        return "user_no_register/library/library";
    }

    @GetMapping("libraries/{nameLibrary}/{isbn}")
    public String libraryBookSpec(@PathVariable String nameLibrary,
                                  @PathVariable long isbn, Model model,
                                  Principal principal)
    {
        Library library = libraryService.findByName(nameLibrary);
        Book book   = bookService.findByIsbn(isbn);

        ArrayList<CopyBook> copies = new ArrayList<>();
        for(CopyBook copyBook: library.getInventory())
            if(copyBook.getIsbn() == isbn && copyBook.getStatus().equals(CopyBook.Status.AVAILABLE))
                copies.add(copyBook);

        model.addAttribute("book", book);
        model.addAttribute("copies", copies);
        model.addAttribute("nameLibrary", nameLibrary);
        model.addAttribute("email", principal != null? principal.getName(): "");
        model.addAttribute("image", library.getImageBase64());

        return "user_no_register/library/book";
    }
}
