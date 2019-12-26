package com.nc.edu.backend.dto;

import java.util.List;

public class CommentPageDto {
    private List<CommentDto> comments;
    private int currentPage;
    private int totalPage;
    private long countOfComments;
    public CommentPageDto() {
    }

    public CommentPageDto(List<CommentDto> comments, int currentPage, int totalPage) {
        this.comments = comments;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public long getCountOfComments() {
        return countOfComments;
    }

    public CommentPageDto(List<CommentDto> comments, int currentPage, int totalPage, long countOfComments) {
        this.comments = comments;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.countOfComments = countOfComments;
    }

    public void setCountOfComments(long countOfComments) {
        this.countOfComments = countOfComments;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
