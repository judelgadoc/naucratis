package com.naucratis.naucratis.model.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
public class Customer extends User {

    public Customer() {
    }

    public Customer(String name, String address, String city, String phone, String email, String password) {
        super(name, address, city, phone, email, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }
}