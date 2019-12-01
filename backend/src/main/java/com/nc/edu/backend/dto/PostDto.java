package com.nc.edu.backend.dto;

import java.util.Date;

public class PostDto {
    private long id;
    private long author;
    private String photoPath;
    private String text;
    private Date date;

    public PostDto() {
    }

    public PostDto(long id, long author, String photoPath, String text, Date date) {
        this.id = id;
        this.author = author;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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
