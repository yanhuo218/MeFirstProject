package com.ManageSystem.pojo;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private int state;
    private int detaildata;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, Integer state, Integer detaildata) {
        this.username = username;
        this.password = password;
        this.state = state;
        this.detaildata = detaildata;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDetaildata() {
        return detaildata;
    }

    public void setDetaildata(Integer detaildata) {
        this.detaildata = detaildata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return state == user.state && detaildata == user.detaildata && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, state, detaildata);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", detaildata=" + detaildata +
                '}';
    }
}
