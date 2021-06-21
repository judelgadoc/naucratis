package com.naucratis.naucratis.model.library;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long isbn;

    @ManyToMany(targetEntity = Author.class)
    private List<Author> authors;

    private String cover;
    private String name;
    private String editorial;
    private String category;
    private String edition;

    @Column(name = "condition_book")
    private String condition;

    private double length;
    private double width;
    private double height;

    private boolean available = true;
    private double  price;

    public Book(long isbn, List<Author> authors, String cover, String name, String editorial,
                String category, String edition, String condition, double price) {
        this.isbn = isbn;
        this.cover = cover;
        this.authors = authors;
        this.name = name;
        this.editorial = editorial;
        this.category = category;
        this.condition = condition;
        this.edition = edition;
        this.price = price;
    }

    public Book() {

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
