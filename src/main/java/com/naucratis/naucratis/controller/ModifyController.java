package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.form.RegistrationBookForm;
import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.service.BookService;
import com.naucratis.naucratis.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("modify")
public class ModifyController
{
    private BookService bookService;
    private LibraryService libraryService;

    public ModifyController(BookService bookService,
                            LibraryService libraryService) {
        this.bookService    = bookService;
        this.libraryService = libraryService;
    }

    @GetMapping("/{name_library}/book/{id_book}")
    public String bookForm(@PathVariable(name = "name_library") String nameLibrary,
                           @PathVariable(name = "id_book") String idBook,
                           Model model)
    {
        Book book = bookService.findById(idBook);


        model.addAttribute("book", book);
        model.addAttribute("listAuthors", book.convertListAuthorsToString());
        model.addAttribute("name_library", nameLibrary);

        return "administrator/modify/form_book";
    }

    @PostMapping("/{name_library}/book/{id_book}")
    public String processBook(@PathVariable(name = "name_library") String nameLibrary,
                              RegistrationBookForm registrationBookForm)
    {
        bookService.update(registrationBookForm);
        return"redirect:/administrator/libraries/" + nameLibrary + "/" + registrationBookForm.getId();
    }
}

