package com.nc.edu.fapi.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class HashTag {
    private long id;
    private String text;

    public HashTag() {
    }

    public HashTag(String text) {
        this.text = text;
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

}
