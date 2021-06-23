package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.form.CopyBookForm;
import com.naucratis.naucratis.model.form.RegistrationBookForm;
import com.naucratis.naucratis.model.library.*;
import com.naucratis.naucratis.model.transaction.Order;
import com.naucratis.naucratis.model.transaction.Purchase;
import com.naucratis.naucratis.model.transaction.Sale;
import com.naucratis.naucratis.model.transaction.Status;
import com.naucratis.naucratis.model.user.Administrator;
import com.naucratis.naucratis.repository.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class LibraryService {

    private LibraryRepository  libraryRepository;

    private BookService        bookService;
    private AuthorService      authorService;
    private SiteService        siteService;
    private CopyBookService    copyBookService;
    private UserService        userService;
    private SaleRepository     saleRepository;
    private PurchaseRepository purchaseRepository;



    public LibraryService(LibraryRepository libraryRepository,
                          BookService bookService,
                          SiteService siteService,
                          CopyBookService copyBookService,
                          AuthorService authorService,
                          UserService userService,
                          SaleRepository saleRepository,
                          PurchaseRepository purchaseRepository) {

        this.authorService      = authorService;
        this.copyBookService    = copyBookService;
        this.bookService        = bookService;
        this.libraryRepository  = libraryRepository;
        this.siteService        = siteService;
        this.userService        = userService;
        this.saleRepository     = saleRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public void addBooks(RegistrationBookForm register) {

        Library library = libraryRepository.findByName(register.getNameLibrary());
        Book book =
                bookService.exitsByIsbn(Long.parseLong(String.valueOf(register.getIsbn())))? bookService.findById(String.valueOf(register.getIsbn())):null;

        if(book == null)
        {
            book = register.toBook();
            bookService.addAuthors(book.getAuthors());
            bookService.save(book);
        }

        if(!library.getBooks().contains(book))
            library.getBooks().add(book);



        for (int i = 0; i < register.getQuantity(); ++i) {
            CopyBook copyBook = new CopyBook(register.getIsbn(), register.getPrice(),
                    register.getSite());
            library.getInventory().add(copyBook);
            copyBookService.save(copyBook);
        }

        libraryRepository.save(library);
    }

    public void addBook(CopyBookForm copyBookForm)
    {
        Library library
                = libraryRepository.findByName(copyBookForm.getNameLibrary());

        CopyBook copyBook = new CopyBook(copyBookForm.getIsbn(), copyBookForm.getPrice(),
                copyBookForm.getSite());

        copyBook.setDescription(copyBookForm.getDescription());
        copyBook.setStatus(CopyBook.Status.valueOf(copyBookForm.getStatus()));
        copyBook.setCondition(copyBookForm.getCondition());

        copyBookService.save(copyBook);

        library.getInventory().add(copyBook);
        libraryRepository.save(library);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void updateBook(CopyBookForm copyBookForm)
    {

    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public boolean eliminateBook(String nameLibrary, long id, long isbn)
    {
        Library library   = libraryRepository.findByName(nameLibrary);
        CopyBook copyBook = copyBookService.findById(id);

        int count = 0;
        for(CopyBook copy: library.getInventory())
            if(copy.getIsbn() == isbn)
                ++count;

        if(count == 1){
            Book bk = null;
            for(Book book: library.getBooks())
                if(book.getIsbn() == isbn)
                    bk = book;
            library.getBooks().remove(bk);
            libraryRepository.save(library);
        }

        library.getInventory().remove(copyBook);
        copyBookService.delete(id);

        return count == 1? true: false;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////


    public void addSite(String nameLibrary, Site site)
    {
        Library library = libraryRepository.findByName(nameLibrary);
        library.getSites().add(site);
        siteService.save(site);
        libraryRepository.save(library);
    }

    public void eliminateSite(String nameLibrary, String addressSite)
    {
        Library library = libraryRepository.findByName(nameLibrary);
        Site site       = siteService.findById(addressSite);
        library.getSites().remove(site);
        libraryRepository.save(library);
        siteService.deleteById(addressSite);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void addLibrary(Library library, Administrator administrator)
    {
        administrator.getLibraries().add(library);
        libraryRepository.save(library);
    }

    public void eliminateLibrary(String nameLibrary, String email)
    {
        Administrator administrator =
                (Administrator) userService.findByEmail(email);

        Library library = findByName(nameLibrary);

        administrator.getLibraries().remove(library);

        eliminate(library);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void finished(String idSale)
    {
        Sale sale          = saleRepository.findById(Long.valueOf(idSale)).get();
        Purchase purchase  = purchaseRepository.findById(sale.getIdPurchase()).get();

        CopyBook copyBook = copyBookService.findById(sale.getCopyBook().getId());
        copyBook.setStatus(CopyBook.Status.SOLD);
        sale.setStatus(Status.FINISHED);
        purchase.setStatus(Status.FINISHED);

        copyBookService.save(copyBook);
        saleRepository.save(sale);
        purchaseRepository.save(purchase);
    }

    public void cancelled(String idSale, String nameLibrary)
    {
        Sale sale         = saleRepository.findById(Long.valueOf(idSale)).get();
        Purchase purchase = purchaseRepository.findById(sale.getIdPurchase()).get();
        Library library   = libraryRepository.findByName(nameLibrary);

        CopyBook copyBook = copyBookService.findById(sale.getCopyBook().getId());
        copyBook.setStatus(CopyBook.Status.AVAILABLE);

        purchase.setStatus(Status.CANCELLED);


        library.getSales().remove(sale);

        saleRepository.delete(sale);

        libraryRepository.save(library);
        copyBookService.save(copyBook);
        purchaseRepository.save(purchase);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void eliminate(Library library)
    {
        libraryRepository.delete(library);
    }

    public void save(Library library)
    {
        libraryRepository.save(library);
    }

    public Library findByName(String name)
    {
        return libraryRepository.findByName(name);
    }

    public Iterable<Library> findAll()
    {
        return libraryRepository.findAll();
    }
}