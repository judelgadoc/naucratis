package com.naucratis.naucratis.model.transaction;

import com.naucratis.naucratis.model.library.Book;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Purchase
{
    private Date createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Book book;

    private String nameLibrary;

    private long   idCopyBook;

    @Column(name = "condition_book")
    private String condition;

    private String description;
    private String site;


    private Status status;
    private double value;

    public Purchase(Book book, long idCopyBook, String nameLibrary, String condition, String description,
                    String site, double value) {
        this.id = id;
        this.book = book;
        this.idCopyBook = idCopyBook;
        this.condition = condition;
        this.description = description;
        this.site = site;
        this.value = value;
        this.status = Status.PROCESSING;
        this.nameLibrary = nameLibrary;
    }

    public Purchase() {

    }

    @PrePersist
    public void createdAt() {
        this.createdAt = new Date();
    }

}
