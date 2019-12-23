package com.nc.edu.backend.dto;

import java.util.List;

public class UserPageDto {
    private List<UserDto> users;
    private int currentPage;
    private int totalPage;

    public UserPageDto() {
    }

    public UserPageDto(List<UserDto> users, int currentPage, int totalPage) {
        this.users = users;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
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
