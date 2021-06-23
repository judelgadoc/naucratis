package com.naucratis.naucratis.model.library;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class CopyBook
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long isbn;

    @Column(name = "condition_book")
    private String condition;

    private String  description;
    private double  price;
    private Status  status;
    private String  site;

    public CopyBook(long isbn, double price, String site) {
        this.isbn = isbn;
        this.condition = "nuevo";
        this.price     = price;
        status         = Status.AVAILABLE;
        this.site      = site;
    }

    public CopyBook(){

    }

    public enum Status{
        AVAILABLE, NOAVAILABLE, SOLD, ENPROCESSSALE
    }

}
