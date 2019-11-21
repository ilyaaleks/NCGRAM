package com.nc.edu.backend.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private String aboutMe;
    private String login;
    private String password;
    private String role;
    private String status;
    private String photoUrl;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)

    private Set<LikeOrDislike> likeOrDislike;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Post> posts;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Claim> claims;
    public User() {
    }


    public User(String name, String surname, String aboutMe, String login, String password, String role, String status, String photoUrl, Set<LikeOrDislike> likeOrDislike, Set<Post> posts) {
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
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Set<LikeOrDislike> getLikeOrDislike() {
        return likeOrDislike;
    }

    public void setLikeOrDislike(Set<LikeOrDislike> likeOrDislike) {
        this.likeOrDislike = likeOrDislike;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}

