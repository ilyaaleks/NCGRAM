package com.nc.edu.backend.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class HashTag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String text;
    @ManyToMany
    @JoinTable(
            name = "hashtag_table",
            joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")}
    )
    private Set<Post> posts=new HashSet<>();

    @Override
    public String toString() {
        return "HashTag{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", postsId="  +
                '}';
    }
    public HashTag(String text, Set<Post> posts) {
        this.text = text;
        this.posts = posts;
    }
    public HashTag() {
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



    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
