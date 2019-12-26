package com.nc.edu.backend.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author;
    @Column(name = "photo_path")
    private String photoPath;
    private String text;
    private Date date;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Claim> claims;
    @ManyToMany(mappedBy = "posts",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<HashTag> hashTags;
    @OneToMany(mappedBy = "postComment",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Comment> comments;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<LikeOrDislike> likeOrDislikes;

    public Post(User author, String photoPath, String text, Date date) {
        this.author = author;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
    }

    public Post(User author, String photoPath, String text, Date date, Set<HashTag> hashTags) {
        this.author = author;
        this.photoPath = photoPath;
        this.text = text;
        this.date = date;
        this.hashTags = hashTags;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(author, post.author) &&
                Objects.equals(photoPath, post.photoPath) &&
                Objects.equals(text, post.text) &&
                Objects.equals(date, post.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, photoPath, text, date);
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

    public Set<Claim> getClaims() {
        return claims;
    }

    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
    }

    public Set<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<LikeOrDislike> getLikeOrDislikes() {
        return likeOrDislikes;
    }

    public void setLikeOrDislikes(Set<LikeOrDislike> likeOrDislikes) {
        this.likeOrDislikes = likeOrDislikes;
    }
}
