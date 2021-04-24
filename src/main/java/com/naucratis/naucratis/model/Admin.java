
package com.naucratis.naucratis.model;

import javax.persistence.*;

@Entity
@Table(name="Admin", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name="user_key",unique = true)
    @OneToOne( cascade = CascadeType.ALL)
    private User user;
   // @OneToOne @JoinColumn(name = "email" )  private User email;
}