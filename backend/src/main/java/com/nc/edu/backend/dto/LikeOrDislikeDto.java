package com.nc.edu.backend.dto;


import com.nc.edu.backend.domain.TypeOfVote;

import java.util.Date;

public class LikeOrDislikeDto {
    private long id;
    private long postId;
    private long authorId;
    private TypeOfVote typeOfVote;
    private Date date;
    private int countOfLikes;
    private int countOfDislikes;
    public LikeOrDislikeDto(long id, long postId, long authorId, TypeOfVote typeOfVote, Date date) {
        this.id = id;
        this.postId = postId;
        this.authorId = authorId;
        this.typeOfVote = typeOfVote;
        this.date = date;
    }

    public LikeOrDislikeDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
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

    public int getCountOfLikes() {
        return countOfLikes;
    }

    public void setCountOfLikes(int countOfLikes) {
        this.countOfLikes = countOfLikes;
    }

    public int getCountOfDislikes() {
        return countOfDislikes;
    }

    public void setCountOfDislikes(int countOfDislikes) {
        this.countOfDislikes = countOfDislikes;
    }
}
