package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.CopyBook;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.model.library.Site;
import com.naucratis.naucratis.model.user.Administrator;
import com.naucratis.naucratis.service.LibraryService;
import com.naucratis.naucratis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/administrator")
@SessionAttributes("role")
public class AdministratorController
{
    private UserService userService;
    private LibraryService libraryService;

    public AdministratorController(UserService userService, LibraryService libraryService) {
        this.userService = userService;
        this.libraryService = libraryService;
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

    /*
    @GetMapping("/libraries")
    public String libraries(Principal principal,
                            Model model)
    {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());
        model.addAttribute("libraries", administrator.getLibraries());
        return "administrator/library/list_libraries";
    }
    */

    @GetMapping("/libraries/{libraryId}")
    public String library(@PathVariable(name = "libraryId") long libraryId,
                          Principal principal, 
                          Model model)
    {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());
        Library library = libraryService.getLibraryById(libraryId).get();
        model.addAttribute("library", library);
        return "administrator/library/library";
    }

    @GetMapping("/libraries/{library_id}/books")
    public String books(@PathVariable(name = "library_id") long libraryId,
                        Principal principal,
                        Model model) throws Exception {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());

        Optional<Library> libraryOptional = libraryService.getLibraryById(libraryId);
        if(!libraryOptional.isPresent()){
            throw new Exception("No se encontro la libreria con id " + libraryId);
        }
        Library library = libraryOptional.get();
        model.addAttribute("library", library);

        return "administrator/library/list_books";
    }

    @GetMapping("/libraries/{name_library}/{isbn}")
    public String book(@PathVariable(name = "name_library") String nameLibrary,
                       @PathVariable(name = "isbn") String isbn,
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

        for(Book bk: library.getBooks())
            if(bk.getIsbn() == Long.parseLong(isbn))
                book = bk;

        if(book == null)
            throw new Exception("El libro no esta en la libreria");

        ArrayList<CopyBook> copies = new ArrayList<>();
        for(CopyBook copyBook: library.getInventory())
        {
            if(copyBook.getIsbn() == Long.parseLong(isbn))
                copies.add(copyBook);
        }

        model.addAttribute("book", book);
        model.addAttribute("copies", copies);
        model.addAttribute("nameLibrary", nameLibrary);

        return "administrator/library/book";
    }

    @GetMapping("/libraries/{name_library}/site/{address_site}")
    public String site(@PathVariable(name = "name_library") String nameLibrary,
                       @PathVariable(name = "address_site") String addressSite,
                       Principal principal,
                       Model model)
    {
        Administrator administrator =
                (Administrator) userService.findByEmail(principal.getName());

        Library library = null;
        for(Library lib: administrator.getLibraries())
            if(nameLibrary.equals(lib.getName()))
                library = lib;

        Site site = null;
        for(Site s: library.getSites())
            if(addressSite.equals(s.getAddress()))
                site = s;

        model.addAttribute("site", site);
        model.addAttribute("name_library", nameLibrary);
        return "administrator/library/site";
    }
}
