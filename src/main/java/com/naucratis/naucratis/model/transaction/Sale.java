package com.naucratis.naucratis.model.transaction;

import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.CopyBook;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Sale
{
    private Date createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Book     book;

    @OneToOne
    private CopyBook copyBook;

    private String email;

    private Status status;

    private long idPurchase;

    public Sale() {

    }

    public Sale(Book book, CopyBook copyBook, String email, long idPurchase) {
        this.book = book;
        this.copyBook = copyBook;
        this.email = email;
        this.status = Status.PROCESSING;
        this.idPurchase = idPurchase;
    }

    @PrePersist
    public void createdAt() {
        this.createdAt = new Date();
    }
}
