package com.nc.edu.backend.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class LikeOrDislike {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
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



    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }





}
