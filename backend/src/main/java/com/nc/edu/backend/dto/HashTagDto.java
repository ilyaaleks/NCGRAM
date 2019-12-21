package com.nc.edu.backend.dto;

public class HashTagDto {
    private long id;
    private String text;

    public HashTagDto() {
    }

    public HashTagDto(long id, String text) {
        this.id = id;
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
