package com.naucratis.naucratis.model;

import javax.persistence.*;

@Entity
@Table(name="Cliente")  //uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Client extends User {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    public Client(String name, String direction, String city, String cel, String email, String password) {
        super(name, direction, city, cel, email, password);
    }

    /*@JoinColumn(name = "user_key", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private User user;*/

}

/* De donde saqué esta vaina de herencia:

https://www.baeldung.com/hibernate-inheritance
 */