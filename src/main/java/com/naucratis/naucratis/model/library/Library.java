package com.naucratis.naucratis.model.library;

import lombok.Data;

import javax.persistence.*;
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

    @OneToMany(targetEntity = Book.class)
    @JoinTable(name = "inventory")
    private List<Book> inventory;

    public Library(String name) {
        this.name = name;
    }

    public Library() {

    }

}
