package com.naucratis.naucratis.model;
import ch.qos.logback.classic.db.names.ColumnName;

import javax.persistence.*;
@Entity
@Table(name="Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "nombre")
    private String name;
    @Column(name = "direccion")
    private String direction;
    @Column(name = "ciudad")
    private String city;
    @Column(name = "telefono")
    private String cel;
    @Column(name= "email")
    private String email;

    @Column(name = "password")
    private String password;
    //private foto


    public User(String name, String direction, String city, String cel, String email, String password) {
        super();
        this.name = name;
        this.direction = direction;
        this.city = city;
        this.cel = cel;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id_user) {
        this.Id = id_user;
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
