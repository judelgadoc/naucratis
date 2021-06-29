package com.naucratis.naucratis.model.library;

import lombok.Data;

import javax.persistence.*;
import java.util.Base64;
import java.util.List;

@Data
@Entity
public class Book
{
    @Id
    private long isbn;

    @ManyToMany(targetEntity = Author.class)
    private List<Author> authors;

    private String name;
    private String editorial;
    private String category;
    private String edition;

    private double length;
    private double width;
    private double height;

    private int numPages;

    @Lob
    private byte[] coverImage;

    public Book(long isbn, String name, String editorial, String category, String edition,
                double length, double width, double height, int numPages) {
        this.isbn = isbn;
        this.name = name;
        this.editorial = editorial;
        this.category = category;
        this.edition = edition;
        this.length = length;
        this.width = width;
        this.height = height;
        this.numPages = numPages;
    }

    public Book() {

    }

    public String getImageBase64()
    {
        if(coverImage == null) {
            return "noImage";
        }
        return Base64.getEncoder().encodeToString(coverImage);
    }

    public String convertListAuthorsToString()
    {
        StringBuilder sb = new StringBuilder();
        for(Author author: authors)
            sb.append(author.getName()+";");
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }
}