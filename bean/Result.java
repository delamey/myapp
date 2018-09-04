package com.example.delamey.myapplication5.bean;

import io.reactivex.Observable;

public class Result<T>  {
    private int  id;
    private String message;
    private T user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Result(int id, String message, T user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }


}
