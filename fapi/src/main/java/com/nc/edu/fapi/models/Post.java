package com.nc.edu.fapi.models;

import java.util.Arrays;
import java.util.Date;

public class Post {
    private long id;
    private long authorId;
    private String photoPath;
    private String text;
    private Date date;
    private HashTag[] hashTags;

    public Post(long id, long authorId, String photoPath, String text, Date date, HashTag[] hashTags) {
        this.id = id;
        this.authorId = authorId;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
        this.hashTags = hashTags;
    }

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", photoPath='" + photoPath + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", hashTags=" + Arrays.toString(hashTags) +
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

    public HashTag[] getHashTags() {
        return hashTags;
    }

    public void setHashTags(HashTag[] hashTags) {
        this.hashTags = hashTags;
    }
}
