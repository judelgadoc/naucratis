package com.naucratis.naucratis.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name="Admin")
public class Admin extends User{
    public Admin() {

    }

    public Admin(String name, String direction, String city, String cel, String email, String password) {
        super(name, direction, city, cel, email, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));
    }

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="user_key",unique = true)
    @OneToOne( cascade = CascadeType.ALL)
    private User user;*/
    // @OneToOne @JoinColumn(name = "email" )  private User email;
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Biblioteca> IdAdmin;

    public Collection<Biblioteca> getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(Collection<Biblioteca> idAdmin) {
        IdAdmin = idAdmin;
    }
}