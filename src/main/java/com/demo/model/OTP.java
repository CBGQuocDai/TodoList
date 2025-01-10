package com.demo.model;

import java.sql.Timestamp;
import java.util.Random;

public class OTP {
    private int id;
    private String email,password;
    private Timestamp createAt,expireAt;
    private int isVerified;

    public OTP() {
    }

    public OTP(String email) {
        Random random = new Random();
        this.email = email;
        StringBuilder pass = new StringBuilder();
        for(int i=0;i<6;i++){
            int val = random.nextInt(10);
            pass.append((char)(val+'0'));
        }
        this.password = pass.toString();
        this.createAt = new Timestamp(System.currentTimeMillis());
        this.expireAt = new Timestamp(System.currentTimeMillis()+62*1000);
        this.isVerified = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Timestamp expireAt) {
        this.expireAt = expireAt;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setVerified(int verified) {
        isVerified = verified;
    }
}
