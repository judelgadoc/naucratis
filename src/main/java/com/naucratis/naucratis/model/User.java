package com.naucratis.naucratis.model;
import  java.util.*;
import javax.persistence.*;
@Entity
@Table(name="Usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Column(name = "nombre")
    private String name;
    @Column(name = "direccion")
    private String direction;
    @Column(name = "ciudad")
    private String city;
    @Column(name = "telefono")
    private String cel;
    @Id
    private String email;

    @Column(name = "password")
    private String password;
    //private foto



    public User(String name, String direction, String city, String cel, String email, String password) {
        this.name = name;
        this.direction = direction;
        this.city = city;
        this.cel = cel;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
