package com.naucratis.naucratis.model.library;

import com.naucratis.naucratis.model.transaction.Order;
import com.naucratis.naucratis.model.transaction.Sale;
import lombok.Data;
import javax.persistence.*;
import java.util.Base64;
import java.util.List;

@Data
@Entity
public class Library
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    @Lob
    private byte[] image;

    @OneToMany
    private List<Site> sites;

    @OneToMany(targetEntity = CopyBook.class)
    @JoinTable(name = "inventory")
    private List<CopyBook> inventory;

    @ManyToMany
    private List<Book> books;

    @OneToMany
    private List<Sale> sales;

    public Library(String name) {
        this.name = name;
    }

    public String getImageBase64()
    {
        return image == null?"": Base64.getEncoder().encodeToString(image);
    }

    public Library() {

    }

}
