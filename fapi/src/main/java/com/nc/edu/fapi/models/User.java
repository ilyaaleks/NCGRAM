package com.nc.edu.fapi.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String aboutMe;
    private String login;
    private String password;
    private String role;
    private String status;
    private String photoUrl;
    private boolean subscribed;
    public User() {
    }

    public User(String name, String surname, String email, String aboutMe, String login, String password, String role, String status, String photoUrl) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.aboutMe = aboutMe;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
        this.photoUrl = photoUrl;
    }

    public User(long id, String name, String surname, String email, String aboutMe, String login, String password, String role, String status, String photoUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.aboutMe = aboutMe;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
        this.photoUrl = photoUrl;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

