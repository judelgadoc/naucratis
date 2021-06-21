package com.naucratis.naucratis.model.library;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Author
{
    @Id
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author() {

    }
}
