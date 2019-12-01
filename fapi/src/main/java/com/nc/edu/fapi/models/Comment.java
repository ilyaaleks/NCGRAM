package com.nc.edu.fapi.models;

import javax.persistence.*;
import java.util.Date;

public class Comment {
    private long id;
    private String text;
    private Date date;

    public Comment() {
    }

    public Comment(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
