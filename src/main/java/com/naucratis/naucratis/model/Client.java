package com.naucratis.naucratis.model;

import javax.persistence.*;


@Entity
@Table(name="Cliente", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
public class Client {

    @Id
    @ManyToOne
    @JoinColumn(name = "FK_correo", nullable = false, updatable = false)
    private User email;
}
