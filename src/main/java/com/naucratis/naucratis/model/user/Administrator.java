package com.naucratis.naucratis.model.user;

import com.naucratis.naucratis.model.library.Library;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Administrator extends User{

    @ManyToMany(targetEntity = Library.class)
    @JoinTable(name = "administra_a")
    private List<Library> libraries;

    public Administrator(String name, String address, String city, String phone, String email, String password) {
        super(name, address, city, phone, email, password);
    }

    public Administrator() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));
    }
}