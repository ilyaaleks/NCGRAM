package com.nc.edu.backend.dto;

import com.nc.edu.backend.domain.HashTag;

import java.util.Date;
import java.util.Set;

public class PostDto {
    private long id;
    private long authorId;
    private String authorLogin;
    private String photoPath;
    private String text;
    private Date date;
    private String authorPhotoPath;
    private Set<HashTagDto> hashTags;
    public PostDto() {
    }

    public PostDto(long id, String authorLogin, String photoPath, String text, Date date, Set<HashTagDto> hashTags) {
        this.id = id;
        this.authorLogin = authorLogin;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
        this.hashTags = hashTags;
    }

    public PostDto(long id, long authorId, String photoPath, String text, Date date, Set<HashTagDto> hashTags) {
        this.id = id;
        this.authorId = authorId;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
        this.hashTags = hashTags;
    }

    public PostDto(long id, long authorId, String authorLogin, String photoPath, String text, Date date, Set<HashTagDto> hashTags) {
        this.id = id;
        this.authorId = authorId;
        this.authorLogin = authorLogin;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
        this.hashTags = hashTags;
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

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
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

    public Set<HashTagDto> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTagDto> hashTags) {
        this.hashTags = hashTags;
    }

    public String getAuthorPhotoPath() {
        return authorPhotoPath;
    }

    public void setAuthorPhotoPath(String authorPhotoPath) {
        this.authorPhotoPath = authorPhotoPath;
    }
}
