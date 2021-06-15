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
@Table(name="Usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty
    @Column(name = "nombre")
    private String name;
    @Column(name = "direccion")
    private String direction;
    @Column(name = "ciudad")
    private String city;
    @Column(name = "telefono")
    @Pattern(regexp="^[0-9]{8}$|^[0-9]{10}$")
    private String cel;
    @Column(name= "email")
    private String email;
    @Column(name = "password")
    @Pattern(regexp="^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$")
    private String password;
    //private foto

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public User() {

    }

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

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
