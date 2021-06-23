package com.naucratis.naucratis.model.form;

import lombok.Data;

@Data
public class CopyBookForm
{
    private String nameLibrary;

    private long id;
    private long isbn;

    private String  condition;

    private String  description;
    private double  price;
    private String  status;
    private String  site;
}
