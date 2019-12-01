package com.nc.edu.fapi.dto;

import com.nc.edu.fapi.models.Post;

import java.util.List;

public class PostPageDto {
    private List<Post> posts;
    private int currentPage;
    private int totalPage;

    public PostPageDto() {
    }

    public PostPageDto(List<Post> posts, int currentPage, int totalPage) {
        this.posts = posts;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
