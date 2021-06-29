package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.form.CopyBookForm;
import com.naucratis.naucratis.model.form.RegistrationBookForm;
import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.CopyBook;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.service.BookService;
import com.naucratis.naucratis.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("modify")
public class ModifyController
{
    private BookService    bookService;
    private LibraryService libraryService;

    public ModifyController(BookService bookService,
                            LibraryService libraryService) {
        this.bookService    = bookService;
        this.libraryService = libraryService;
    }

    @GetMapping("/{libraryId}/book/{id_book}")
    public String bookForm(@PathVariable(name = "libraryId") long libraryId,
                           @PathVariable(name = "id_book") String idBook,
                           Model model) throws Exception {
        CopyBook copyBook = bookService.findCopyBookById(Long.parseLong(idBook));

        Optional<Library> libraryOptional = libraryService.getLibraryById(libraryId);
        if(!libraryOptional.isPresent()){
            throw new Exception("No se encuentra la biblioteca con id " + libraryId);
        }
        Library library = libraryOptional.get();
        model.addAttribute("copyBook", copyBook );
        model.addAttribute("name_library", library.getName());
        model.addAttribute("libraryId", libraryId);
        model.addAttribute("sites", library.getSites());

        return "administrator/modify/form_book";
    }

    @PostMapping("/{libraryId}/book/{id_book}")
    public String processBook(@PathVariable(name = "libraryId") long libraryId,
                              CopyBookForm copyBookForm)
    {
        bookService.updateCopy(copyBookForm);
        return"redirect:/administrator/libraries/" + libraryId + "/" + copyBookForm.getIsbn();
    }
}

