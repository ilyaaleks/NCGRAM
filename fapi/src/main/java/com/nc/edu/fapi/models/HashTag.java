package com.nc.edu.fapi.models;

import java.util.Arrays;

public class HashTag {
    private long id;
    private String text;
    private long[] postsId;

    @Override
    public String toString() {
        return "HashTag{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", postsId=" + Arrays.toString(postsId) +
                '}';
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

    public long[] getPostsId() {
        return postsId;
    }

    public void setPostsId(long[] postsId) {
        this.postsId = postsId;
    }

    public HashTag(long id, String text, long[] postsId) {
        this.id = id;
        this.text = text;
        this.postsId = postsId;
    }
}
