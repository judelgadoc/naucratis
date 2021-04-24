package com.naucratis.naucratis.model;

import javax.persistence.*;

@Entity
@Table(name="Admin", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
public class Admin {
    @Id
    @ManyToOne
    @JoinColumn(name = "FK_correo", nullable = false, updatable = false)
    private User email;
}
