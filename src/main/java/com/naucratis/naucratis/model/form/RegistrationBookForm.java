package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.library.Author;
import com.naucratis.naucratis.model.library.Book;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class RegistrationBookForm
{
    private String  nameLibrary;

    private long    isbn;

    private List<Author> authors;
    private String       list_authors;

    private String name;
    private String editorial;
    private String category;
    private String condition;
    private String edition;

    private double length;
    private double width;
    private double height;

    private boolean available;
    private double  price;

    private int numPages;

    private int quantity;

    private String site;

    private MultipartFile cover;

    public Book toBook() {

        stringToListAuthors();

        Book book = new Book(isbn, name, editorial, category, edition,
                length, width, height, numPages);

        book.setAuthors(authors);

        try {
            book.setCoverImage(cover.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return book;
    }

    public List<Author> getAuthors()
    {
        stringToListAuthors();
        return authors;
    }

    private void stringToListAuthors()
    {
        this.authors = new ArrayList<>();

        for(String author: list_authors.split(";"))
            authors.add(new Author(author));
    }

    public boolean isAvailable() {
        return available;
    }
}

