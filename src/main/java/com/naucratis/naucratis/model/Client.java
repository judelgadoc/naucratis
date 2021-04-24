package com.naucratis.naucratis.model;

import javax.persistence.*;

@Entity
@Table(name="Cliente", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name="user_key",unique = true)
    @OneToOne( cascade = CascadeType.ALL)
    private User user;



}
