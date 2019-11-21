package com.nc.edu.backend.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="user_id")
    private User author;
    private String photoPath;
    private String text;
    private Date date;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Claim> claims;
    @ManyToMany(mappedBy = "posts")
    private Set<HashTag> hashTags;
    @OneToMany(mappedBy = "postComment",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Comment> comments;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<LikeOrDislike> likeOrDislikes;
    public Post() {
    }
    public Post(User author, String photoPath, String text, Date date, Set<Claim> claims, Set<HashTag> hashTags, Set<Comment> comments, Set<LikeOrDislike> likeOrDislikes) {
        this.author = author;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
        this.claims = claims;
        this.hashTags = hashTags;
        this.comments = comments;
        this.likeOrDislikes = likeOrDislikes;
    }
    public Set<LikeOrDislike> getLikeOrDislikes() {
        return likeOrDislikes;
    }

    public void setLikeOrDislikes(Set<LikeOrDislike> likeOrDislikes) {
        this.likeOrDislikes = likeOrDislikes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }





    public Set<Claim> getClaims() {
        return claims;
    }

    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorId=" + author.getName() +
                ", photoPath='" + photoPath + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", hashTags=" +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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



    public Set<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTag> hashTags) {
        this.hashTags = hashTags;
    }
}
