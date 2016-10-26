package com.demo.model;

/**
 * Created by Pinggang Yu on 2016/10/25.
 */
public class User {
    private int userId;
    private String emailAddress;
    private String password;
    private String username;
    private boolean enable;
    public User(){

    }
    public User(String username, String password, String emailAddress) {
        this(1, username, password, emailAddress, false);
    }
    public User(int userId, String username, String password, String emailAddress, boolean enable) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.enable = enable;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", enable=" + enable +
                '}';
    }
}
