package com.naucratis.naucratis.model.user;

import com.naucratis.naucratis.model.transaction.Order;
import com.naucratis.naucratis.model.transaction.Purchase;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Customer extends User {

    @OneToMany
    private List<Purchase> purchases;

    @OneToMany
    private List<Order> shoppingCart;

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