package com.nc.edu.backend.dto;

import java.util.Date;

public class CommentDto {
    private long id;
    private String text;
    private Date date;
    private long postId;
    private long userId;
    private String userLogin;
    private String authorPhotoPath;

    public CommentDto() {
    }

    public CommentDto(long id, String text, Date date, long postId, long userId, String userLogin, String authorPhotoPath) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.postId = postId;
        this.userId = userId;
        this.userLogin = userLogin;
        this.authorPhotoPath = authorPhotoPath;
    }

    public CommentDto(String text, long postId, long userId, String userLogin, String authorPhotoPath) {
        this.text = text;
        this.postId = postId;
        this.userId = userId;
        this.userLogin = userLogin;
        this.authorPhotoPath = authorPhotoPath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getAuthorPhotoPath() {
        return authorPhotoPath;
    }

    public void setAuthorPhotoPath(String authorPhotoPath) {
        this.authorPhotoPath = authorPhotoPath;
    }
}
