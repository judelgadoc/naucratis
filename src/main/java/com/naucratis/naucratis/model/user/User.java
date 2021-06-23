package com.naucratis.naucratis.model.user;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;

@Entity
@Data
public class User implements UserDetails {

    private String name;

    private String address;
    private String city;
    private String phone;

    @Id
    private String email;

    @Lob
    private byte[] image;

    private String password;

    public User() {

    }

    public User(String name, String address, String city, String phone, String email, String password) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getImageBase64()
    {
        return image == null ?"": Base64.getEncoder().encodeToString(image);
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
}
