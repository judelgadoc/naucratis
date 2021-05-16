package com.naucratis.naucratis.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name="Libro_prestamo")
public class Libro_prestamo extends Book {
    public Libro_prestamo(String isbn, String name, String editorial, String category, String status, boolean avalible) {
        super(isbn, name, editorial, category, status, avalible);
    }

    public Libro_prestamo() {
    }
}
