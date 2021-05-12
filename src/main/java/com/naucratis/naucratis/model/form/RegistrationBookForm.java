package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.library.Author;
import com.naucratis.naucratis.model.library.Book;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class RegistrationBookForm
{

    private Long id;

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

    public Book toBook() throws IOException {

        stringToListAuthors();

        Book book = new Book(isbn, authors, name, editorial, category, edition,
                condition, price);

        book.setLength(length);
        book.setWidth(width);
        book.setHeight(height);

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

