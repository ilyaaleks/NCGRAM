package com.nc.edu.fapi.models;

import java.util.Date;

public class Dislike {
    private long id;
    private long authorId;
    private Date date;

    @Override
    public String toString() {
        return "Dislike{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", date=" + date +
                '}';
    }

    public long getId() {
        return id;
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

    public Dislike() {
    }

    public Dislike(long id, long authorId, Date date) {
        this.id = id;
        this.authorId = authorId;
        this.date = date;
    }
}
