package com.nc.edu.fapi.models;

import java.util.Date;

public class Like {
    private long id;
    private long authorId;
    private Date date;

    public Like() {
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", date=" + date +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Like(long id, long authorId, Date date) {
        this.id = id;
        this.authorId = authorId;
        this.date = date;
    }
}
