package com.naucratis.naucratis.model.library;

import lombok.Data;

import javax.persistence.*;
import java.util.Base64;
import java.util.List;

@Data
@Entity
public class Book
{
    @Id
    private long isbn;

    @ManyToMany(targetEntity = Author.class)
    private List<Author> authors;

    private String name;
    private String editorial;
    private String category;
    private String edition;

    private double length;
    private double width;
    private double height;

    private int numPages;

    @Lob
    private byte[] coverImage;

    public Book(long isbn, String name, String editorial, String category, String edition,
                double length, double width, double height, int numPages) {
        this.isbn = isbn;
        this.name = name;
        this.editorial = editorial;
        this.category = category;
        this.edition = edition;
        this.length = length;
        this.width = width;
        this.height = height;
        this.numPages = numPages;
    }

    public Book() {

    }

    public String getImageBase64()
    {
        return Base64.getEncoder().encodeToString(coverImage);
    }

    public String convertListAuthorsToString()
    {
        StringBuilder sb = new StringBuilder();
        for(Author author: authors)
            sb.append(author.getName()+";");
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
