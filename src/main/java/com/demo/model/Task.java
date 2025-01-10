package com.demo.model;

public class Task {
    private static int cnt=0;
    private int id;
    private String name,status;

    public Task() {}

    public Task(String name) {
        this.id=++cnt;
        this.name= name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
