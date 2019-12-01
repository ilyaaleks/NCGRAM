package com.nc.edu.fapi.models;

import javax.persistence.*;
import java.util.Date;

public class LikeOrDislike {
    private long id;
    private Post post;
    private User author;
    private TypeOfVote typeOfVote;
    private Date date;

    public LikeOrDislike() {
    }

    public LikeOrDislike(Post post, User author, TypeOfVote typeOfVote, Date date) {
        this.post = post;
        this.author = author;
        this.typeOfVote = typeOfVote;
        this.date = date;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public TypeOfVote getTypeOfVote() {
        return typeOfVote;
    }

    public void setTypeOfVote(TypeOfVote typeOfVote) {
        this.typeOfVote = typeOfVote;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
