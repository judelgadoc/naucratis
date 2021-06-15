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
@Table(name="Ventas", uniqueConstraints = @UniqueConstraint(columnNames = "Id_venta"))
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_venta;

    @NotEmpty
    @Column(name = "Id_biblioteca")
    private Long Id_biblioteca;
    @Column(name = "Correo")
    private String correo;
    @Column(name="Fecha")
    private Date fecha;
    @Column (name="Precio")
    private Long precio;
    public Venta() {
    }

    public Venta(Long id_venta, Long id_biblioteca, String correo, Date fecha, Long precio) {
        Id_venta = id_venta;
        Id_biblioteca = id_biblioteca;
        this.correo = correo;
        this.fecha = fecha;
        this.precio = precio;
    }

    public Long getId_venta() {
        return Id_venta;
    }

    public void setId_venta(Long id_venta) {
        Id_venta = id_venta;
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

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }
}
