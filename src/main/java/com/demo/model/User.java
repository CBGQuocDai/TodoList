package com.demo.model;

public class User {
    private int id;
    private String email, password;
    private int verify;

    public User() {
    }

    public User(String password, String email, int verify) {
        this.password = password;
        this.email = email;
        this.verify = verify;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
