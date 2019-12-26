package com.nc.edu.backend.dto;

import java.util.Date;

public class ClaimDto {
    private long id;
    private long postId;
    private Date date;
    private String reason;
    private String status;
    private long authorId;

    public ClaimDto() {
    }

    public ClaimDto(long id, long postId, Date date, String reason, String status, long authorId) {
        this.id = id;
        this.postId = postId;
        this.date = date;
        this.reason = reason;
        this.status = status;
        this.authorId = authorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
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

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
