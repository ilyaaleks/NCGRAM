package com.nc.edu.backend.dto;

import com.nc.edu.backend.domain.Post;

import java.util.List;

public class PostPageDto {
    private List<PostDto> posts;
    private int currentPage;
    private int totalPage;

    public PostPageDto() {
    }

    public PostPageDto(List<PostDto> posts, int currentPage, int totalPage) {
        this.posts = posts;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
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
