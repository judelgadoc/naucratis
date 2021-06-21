package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.form.RegistrationBookForm;
import com.naucratis.naucratis.model.library.Author;
import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.repository.AuthorRepository;
import com.naucratis.naucratis.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
        this.bookRepository   = bookRepository;
    }

    public void save(Book book)
    {
        bookRepository.save(book);
    }

    public Book findById(String id)
    {
        return bookRepository.findById(Long.valueOf(id)).get();
    }

    public void eliminate(Book book)
    {
        bookRepository.delete(book);
    }

    public void update(RegistrationBookForm registrationBookForm)
    {
        Book book = bookRepository.findById(registrationBookForm.getId()).get();

        book.setName(registrationBookForm.getName());
        book.setIsbn(registrationBookForm.getIsbn());
        book.setEditorial(registrationBookForm.getEditorial());
        book.setCategory(registrationBookForm.getCategory());
        book.setCondition(registrationBookForm.getCondition());
        book.setAvailable(registrationBookForm.isAvailable());
        book.setLength(registrationBookForm.getLength());
        book.setWidth(registrationBookForm.getWidth());
        book.setHeight(registrationBookForm.getHeight());
        book.setPrice(registrationBookForm.getPrice());

        for(Author author: registrationBookForm.getAuthors())
            if(!authorRepository.existsById(author.getName()))
                authorRepository.save(author);

        book.setAuthors(registrationBookForm.getAuthors());

        bookRepository.save(book);
    }
}
