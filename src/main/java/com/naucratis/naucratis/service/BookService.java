package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.form.CopyBookForm;
import com.naucratis.naucratis.model.form.RegistrationBookForm;
import com.naucratis.naucratis.model.library.Author;
import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.CopyBook;
import com.naucratis.naucratis.repository.AuthorRepository;
import com.naucratis.naucratis.repository.BookRepository;
import com.naucratis.naucratis.repository.CopyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
    private BookRepository     bookRepository;
    private AuthorRepository   authorRepository;
    private CopyBookRepository copyBookRepository;
    private AuthorService      authorService;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       CopyBookRepository copyBookRepository,
                       AuthorService authorService)
    {
        this.copyBookRepository = copyBookRepository;
        this.authorRepository = authorRepository;
        this.bookRepository   = bookRepository;
        this.authorService    = authorService;
    }

    public Book findByIsbn(long isbn)
    {
        return bookRepository.findById(isbn).get();
    }

    public void save(Book book)
    {
        bookRepository.save(book);
    }

    public Book findById(String id)
    {
        return bookRepository.findById(Long.valueOf(id)).get();
    }

    public CopyBook findCopyBookById(long id)
    {
        return copyBookRepository.findById(id).get();
    }

    public void eliminate(Book book)
    {
        bookRepository.delete(book);
    }

    public boolean exitsByIsbn(long isbn)
    {
        return bookRepository.existsById(isbn);
    }

    public void updateCopy(CopyBookForm copyBookForm)
    {
        CopyBook copyBook = copyBookRepository.findById(copyBookForm.getId()).get();

        copyBook.setStatus(CopyBook.Status.valueOf(copyBookForm.getStatus()));
        copyBook.setCondition(copyBookForm.getCondition());
        copyBook.setDescription(copyBookForm.getDescription());
        copyBook.setPrice(copyBookForm.getPrice());

        copyBook.setSite(copyBookForm.getSite());

        copyBookRepository.save(copyBook);
    }
/*
    public void update(RegistrationBookForm registrationBookForm)
    {
        Book book = bookRepository.findById(registrationBookForm.getId()).get();

        book.setName(registrationBookForm.getName());
        book.setIsbn(registrationBookForm.getIsbn());
        book.setEditorial(registrationBookForm.getEditorial());
        book.setCategory(registrationBookForm.getCategory());
        book.setLength(registrationBookForm.getLength());
        book.setWidth(registrationBookForm.getWidth());
        book.setHeight(registrationBookForm.getHeight());
        
        for(Author author: registrationBookForm.getAuthors())
            if(!authorRepository.existsById(author.getName()))
                authorRepository.save(author);

        try {
            if(!registrationBookForm.getCover().isEmpty())
                book.setCoverImage(registrationBookForm.getCover().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        book.setAuthors(registrationBookForm.getAuthors());

        bookRepository.save(book);
    }

 */

    public void addAuthors(List<Author> authors)
    {
        for (Author author : authors)
            if (!authorService.existsByName(author.getName()))
                authorService.save(author);
    }


    public boolean searchBook(long isbn, Book book)
    {
        book = bookRepository.existsById(isbn)? bookRepository.findById(isbn).get():null;
        return book != null? true: false;
    }
}
