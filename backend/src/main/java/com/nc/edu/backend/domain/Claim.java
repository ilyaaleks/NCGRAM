package com.nc.edu.backend.domain;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Claim {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    private Date date;
    private String reason;
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    public Claim() {
    }
    public Claim(Post post, Date date, String reason, String status, User author) {
        this.post = post;
        this.date = date;
        this.reason = reason;
        this.status = status;
        this.author = author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", postId=" + post.getId()+
                ", date=" + date +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
