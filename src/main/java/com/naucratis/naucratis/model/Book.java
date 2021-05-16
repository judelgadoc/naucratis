package com.naucratis.naucratis.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.Collection;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="Libro")
public class Book {
    @Id
    private String Isbn;

    @NotEmpty
    @Column(name = "nombre")
    private String name;
    @Column(name = "editorial")
    private String editorial;
    @Column(name = "categoria")
    private String category;
    @Column(name = "estado")
    private String status;
    @Column(name = "disponible")
    private boolean avalible;

    public Book(String isbn, String name, String editorial, String category, String status, boolean avalible) {
        Isbn = isbn;
        this.name = name;
        this.editorial = editorial;
        this.category = category;
        this.status = status;
        this.avalible = avalible;
    }



    public Book() {

    }



    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAvalible() {
        return avalible;
    }

    public void setAvalible(boolean avalible) {
        this.avalible = avalible;
    }
}
