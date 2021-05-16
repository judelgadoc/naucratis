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

@Table(name="Biblioteca", uniqueConstraints = @UniqueConstraint(columnNames = "Id"))
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty
    @Column(name = "nombre")
    private String name;
    @Column(name = "direccion")
    private String direction;


    public Biblioteca() {

    }
    public Biblioteca(String name, String direction) {
        super();
        this.name = name;
        this.direction = direction;
    }

    public Long getId() { return Id; }

    public void setId(Long Id) {
        this.Id = Id;
    }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }



    public String getDirection() { return direction; }

    public void setDirection(String direction) { this.direction = direction; }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "IdAdmin")
    private Collection<Admin> IdBiblioteca;

    public Collection<Admin> getIdBiblioteca() {
        return IdBiblioteca;
    }

    public void setIdBiblioteca(Collection<Admin> idBiblioteca) {
        IdBiblioteca = idBiblioteca;
    }
}
