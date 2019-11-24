package com.nc.edu.backend.domain;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;
    private String text;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post postComment;

    public Comment() {
    }

    public Comment(User author, String text, Date date, Post postComment) {
        this.author = author;
        this.text = text;
        this.date = date;
        this.postComment = postComment;
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

    public Post getPostComment() {
        return postComment;
    }

    public void setPostComment(Post postComment) {
        this.postComment = postComment;
    }
}
