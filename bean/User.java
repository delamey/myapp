package com.example.delamey.myapplication5.bean;

import java.net.URL;

public class User {
    private String name;
    private int id;
    private String userEmail;
    private String type;
    private int age;
    private int  url;
    private int number;

    public User(int url, String type, int number) {
        this.url = url;
        this.type=type;
        this.number=number;
    }

    public int getUrl() {
        return url;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setUrl(int url) {
        this.url = url;

    }

    public String getUserEmail() {
        return userEmail;
    }



    public User(String name,  String userEmail, String type) {
        this.name = name;
        this.userEmail = userEmail;

        this.type=type;
    }


    public User(String name,String userEmail, int age ,String type) {
        this.name=name;
        this.userEmail = userEmail;
        this.age = age;
        this.type=type;
    }


    public User(String name,String type) {
        this.name = name;
        this.type=type;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
