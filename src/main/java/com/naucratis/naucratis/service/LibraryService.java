package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.repository.LibraryRepository;
import org.springframework.stereotype.Service;

@Service
public class LibraryService
{
    private BookService       bookService;
    private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository,
                          BookService bookService)
    {
        this.bookService = bookService;
        this.libraryRepository = libraryRepository;
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

    public void eliminateBook(String nameLibrary, String idBook)
    {
        Library library = libraryRepository.findByName(nameLibrary);
        Book book       = bookService.findById(idBook);

        library.getInventory().remove(book);
        bookService.eliminate(book);
    }

    public void eliminate(Library library)
    {
        libraryRepository.delete(library);
    }
}