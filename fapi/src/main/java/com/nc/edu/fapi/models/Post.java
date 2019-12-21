package com.nc.edu.fapi.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


public class Post {
    private long id;
    private String authorLogin;
    private String photoPath;
    private String text;
    private Date date;
    private Set<HashTag> hashTags;
    private String hashTagsStr="";
    public Post() {
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public Post(String authorLogin, String photoPath, String text, Date date, Set<HashTag> hashTags) {
        this.authorLogin = authorLogin;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
        this.hashTags = hashTags;
    }

    public Set<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getHashTagsStr() {
        return hashTagsStr;
    }

    public void setHashTagsStr(String hashTagsStr) {
        this.hashTagsStr = hashTagsStr;
    }
}
