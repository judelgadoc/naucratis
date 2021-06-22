package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.library.Site;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class RegisterSiteForm
{
    private String type;
    private String address;
    private String open;
    private String closed;

    public Site toSite()
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Date op = null;
        Date cl = null;

        try {
            op = format.parse(open);
            cl = format.parse(closed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Site(type, address, op, cl);
    }
}

