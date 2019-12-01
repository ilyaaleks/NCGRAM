package com.nc.edu.fapi.models;

import javax.persistence.*;
import java.util.Date;

public class Claim {
    private long id;
    private Post post;
    private Date date;
    private String reason;
    private String status;
    private User author;

    public Claim(Post post, Date date, String reason, String status, User author) {
        this.post = post;
        this.date = date;
        this.reason = reason;
        this.status = status;
        this.author = author;
    }

    public Claim() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
