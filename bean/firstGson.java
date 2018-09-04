package com.example.delamey.myapplication5.bean;

public class firstGson {

    public firstGson(String name) {
        this.name = name;
    }

    public firstGson(String name, String age, int gender, String school) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.school = school;
    }

    /**
     * name : mrxi
     * age : 24
     * gender : 1
     * school : bupt

     */

    private String name;
    private String age;
    private int gender;
    private String school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return getName()+" "+getGender()+" "+getAge()+" "+getSchool();
    }
}
