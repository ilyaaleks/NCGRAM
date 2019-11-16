package com.nc.edu.fapi.models;

import java.util.Date;

public class Claim {
    private long id;
    private long postId;
    private Date date;
    private String reason;
    private String status;

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", postId=" + postId +
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

    public Claim() {
    }

    public Claim(long id, long postId, Date date, String reason, String status) {
        this.id = id;
        this.postId = postId;
        this.date = date;
        this.reason = reason;
        this.status = status;
    }
}
