package com.naucratis.naucratis.model.library;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Site
{

    @Id
    private String address;

    private String type;
    private Date open;
    private Date closed;

    public Site(String type, String address, Date open, Date closed) {
        this.type = type;
        this.address = address;
        this.open = open;
        this.closed = closed;
    }

    public Site() {

    }
}