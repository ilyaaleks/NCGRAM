package com.nc.edu.backend.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<User> getSubscribtions() {
        return subscribtions;
    }

    public void setSubscribtions(Set<User> subscribtions) {
        this.subscribtions = subscribtions;
    }

    @Column(name = "about_me")
    private String aboutMe;
    private String login;
    private String password;
    private String role;
    private String status;
    @Column(name="photo_url")
    private String photoUrl;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<LikeOrDislike> likeOrDislike;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Claim> claims;
    @ManyToMany
    @JoinTable(
            name="user_subscriptions",
            joinColumns = {@JoinColumn(name="subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name="channel_id")}
    )

    private Set<User> subscribers=new HashSet<>();
    @ManyToMany
    @JoinTable(
            name="user_subscriptions",
            joinColumns = {@JoinColumn(name="channel_id")},
            inverseJoinColumns = {@JoinColumn(name="subscriber_id")}
    )
    private Set<User> subscribtions=new HashSet<>();

    public User() {
    }

    public User(String name, String surname, String aboutMe, String login, String password, String role, String status, String photoUrl, List<LikeOrDislike> likeOrDislike, List<Post> posts, List<Claim> claims) {
        this.name = name;
        this.surname = surname;
        this.aboutMe = aboutMe;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
        this.photoUrl = photoUrl;
        this.likeOrDislike = likeOrDislike;
        this.posts = posts;
        this.claims = claims;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<LikeOrDislike> getLikeOrDislike() {
        return likeOrDislike;
    }

    public void setLikeOrDislike(List<LikeOrDislike> likeOrDislike) {
        this.likeOrDislike = likeOrDislike;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
}

