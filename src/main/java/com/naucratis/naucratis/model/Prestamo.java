package com.naucratis.naucratis.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;


@Entity
@Table(name="Prestamo", uniqueConstraints = @UniqueConstraint(columnNames = "Id_prestamo"))
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_prestamo;
    @NotEmpty
    @Column(name = "Id_biblioteca")
    private Long Id_biblioteca;
    @Column(name = "Correo")
    private String correo;
    @Column(name = "Fecha")
    private Date fecha;

    public Prestamo() {
    }

    public Prestamo(Long id_biblioteca, String correo, Date fecha) {

        Id_biblioteca = id_biblioteca;
        this.correo = correo;
        this.fecha = fecha;
    }

    public Long getId_prestamo() {
        return Id_prestamo;
    }

    public void setId_prestamo(Long id_prestamo) {
        Id_prestamo = id_prestamo;
    }

    public Long getId_biblioteca() {
        return Id_biblioteca;
    }

    public void setId_biblioteca(Long id_biblioteca) {
        Id_biblioteca = id_biblioteca;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

