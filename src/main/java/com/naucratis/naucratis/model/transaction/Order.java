package com.naucratis.naucratis.model.transaction;

import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.CopyBook;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "order_b")
public class Order
{
    private Date createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Book book;


    @OneToOne
    private CopyBook copyBook;

    private String email;
    private String nameLibrary;


    public Order() {

    }

    public Order(Book book, CopyBook copyBook, String email, String nameLibrary) {
        this.book        = book;
        this.copyBook    = copyBook;
        this.email       = email;
        this.nameLibrary = nameLibrary;
    }

    @PrePersist
    public void createdAt() {
        this.createdAt = new Date();
    }
}
