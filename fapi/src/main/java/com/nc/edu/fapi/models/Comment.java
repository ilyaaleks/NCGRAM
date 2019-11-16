package com.nc.edu.fapi.models;

import java.util.Date;

public class Comment {
    private long id;
    private long authorId;
    private String text;
    private Date date;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", text='" + text + '\'' +
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

    public Comment(long id, long authorId, String text, Date date) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.date = date;
    }

    public Comment() {
    }
}
