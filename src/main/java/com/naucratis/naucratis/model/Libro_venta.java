package com.naucratis.naucratis.model;


import javax.persistence.*;

@Entity
@Table(name="Libro_venta")
public class Libro_venta extends Book{
    private Long precio;
    public Libro_venta() {
    }

    public Libro_venta(String isbn, String name, String editorial, String category, String status, boolean avalible, Long precio) {
        super(isbn, name, editorial, category, status, avalible);
        this.precio = precio;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }
}
