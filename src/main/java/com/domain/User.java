package com.domain;

/**
 * Created by Ma on 17/2/17.
 */
public class User {
    private String user_name;
    private String password;
    private int permission;
    private String nickname;

    public User(String user_name, String password, int permission, String nickname) {
        this.user_name = user_name;
        this.password = password;
        this.permission = permission;
        this.nickname = nickname;
    }

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
        this.permission = 0;
        this.nickname = "new user";
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public int getPermission() {
        return permission;
    }

    public String getNickname() {
        return nickname;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
