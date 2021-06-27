package com.naucratis.naucratis.service;

import com.naucratis.naucratis.exception.LibraryNotFoundException;
import com.naucratis.naucratis.model.dto.LibraryRequestDto;
import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void save(LibraryRequestDto libraryRequest) {
        Library library = new Library();
        library.setName(libraryRequest.getName());
        library.setAddress(libraryRequest.getAddress());
        library.setContactPhone(libraryRequest.getContactPhone());
        libraryRepository.save(library);
    }

    public Optional<Library> getLibraryById(long libraryId){
        return libraryRepository.findById(libraryId);
    }

    public void updateLibrary(LibraryRequestDto libraryRequest) throws LibraryNotFoundException {
        Optional<Library> library = libraryRepository.findById(libraryRequest.getId());
        if(!library.isPresent()) {
            throw new LibraryNotFoundException("Librería no encontrada");
        }
        Library updatedLibrary = library.get();
        updatedLibrary.setName(libraryRequest.getName());
        updatedLibrary.setAddress(libraryRequest.getAddress());
        updatedLibrary.setContactPhone(libraryRequest.getContactPhone());
        libraryRepository.save(updatedLibrary);
    }

    public void deleteLibraryById(Long libraryId){
        libraryRepository.deleteById(libraryId);
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